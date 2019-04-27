package br.com.neto.reserva.repository.reserva;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.neto.reserva.model.Filial;
import br.com.neto.reserva.model.Reserva;
import br.com.neto.reserva.model.Sala;
import br.com.neto.reserva.repository.filter.ReservaFilter;

public class ReservaRepositoryImpl implements ReservaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Reserva buscarReservaPorHorarioSalaLocal(Reserva reserva) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Reserva> criteria = builder.createQuery(Reserva.class);
        Root<Reserva> root = criteria.from(Reserva.class);

        Join<Reserva, Filial> joinFilial = root.join("filial", JoinType.INNER);
        Join<Reserva, Sala> joinSala = root.join("sala", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal( joinFilial.get("id"), reserva.getFilial().getId() ));

        predicates.add(builder.equal( joinSala.get("id"), reserva.getSala().getId() ));

        predicates.add(builder.greaterThanOrEqualTo(root.get("inicio"), reserva.getInicio()));

        predicates.add(builder.lessThanOrEqualTo(root.get("fim"), reserva.getFim()));

        Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);

        criteria.where(arrayPredicates);

        TypedQuery<Reserva> query = this.manager.createQuery(criteria);
        query.setMaxResults(1);

        List<Reserva> resultList = query.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Page<Reserva> filtrar(ReservaFilter reservaFilter, Pageable pageable) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Reserva> criteria = builder.createQuery(Reserva.class);
        Root<Reserva> root = criteria.from(Reserva.class);

        Join<Reserva, Filial> joinFilial = root.join("filial", JoinType.INNER);
        Join<Reserva, Sala> joinSala = root.join("sala", JoinType.INNER);

        Predicate[] predicates = this.criarRestricoes(reservaFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Reserva> query = this.manager.createQuery(criteria);
        this.adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, this.total(reservaFilter));
    }

    private Predicate[] criarRestricoes(ReservaFilter reservaFilter, CriteriaBuilder builder, Root<Reserva> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (reservaFilter.getDataInicial() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("inicio"), reservaFilter.getDataInicial()));
        }

        if (reservaFilter.getDataFinal() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("fim"), reservaFilter.getDataFinal() ));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Reserva> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(ReservaFilter reservaFilter) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Reserva> root = criteria.from(Reserva.class);

        Join<Reserva, Filial> joinFilial = root.join("filial", JoinType.INNER);
        Join<Reserva, Sala> joinSala = root.join("sala", JoinType.INNER);

        Predicate[] predicates = this.criarRestricoes(reservaFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return this.manager.createQuery(criteria).getSingleResult();
    }


}

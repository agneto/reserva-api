package br.com.neto.reserva.repository.reserva;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.neto.reserva.model.Reserva;
import br.com.neto.reserva.repository.filter.ReservaFilter;

public class ReservaRepositoryImpl implements ReservaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Reserva buscarReservaPorHorarioSalaLocal(Reserva reserva) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Reserva> criteria = builder.createQuery(Reserva.class);
        Root<Reserva> root = criteria.from(Reserva.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal( root.get("filial").get("id"), reserva.getFilial().getId() ));

        predicates.add(builder.equal( root.get("sala").get("id"), reserva.getSala().getId() ));

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
        // TODO implementação de reservas paginadas
        return null;
    }

}

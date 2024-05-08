package br.com.personmanagement.infrastructure.specifications;

import br.com.personmanagement.domain.entity.People;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PeopleSpecifications implements Specification<People> {

    private static final long serialVersionUID = 1L;

    public String name;

    public PeopleSpecifications(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<People> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(this.name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + this.name + "%"));
        }
        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));


    }
}

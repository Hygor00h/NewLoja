package com.loja.newloja.infra.filter.specification;


import com.loja.newloja.infra.filter.FindProdutoFilter;
import com.loja.newloja.infra.model.entity.produto.ProdutoEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class EventSpecification {

    public static Specification<ProdutoEntity> filter(FindProdutoFilter filter){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter == null){
                return criteriaBuilder.conjunction();
            }

            if(StringUtils.hasText(filter.getNome())){
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nome")),
                        "%" + filter.getNome().toLowerCase() + "%"
                ));
            }

            if(StringUtils.hasText(filter.getDescricao())){
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("descricao")),
                        "%" + filter.getDescricao().toLowerCase() + "+"
                ));
            }

            if(StringUtils.hasText(filter.getCategoria())){
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("categoria")),
                        "&" + filter.getDescricao().toLowerCase() + "%"
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}

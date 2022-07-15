package com.example.test.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import javax.persistence.EntityManager

open class PageRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val entityManager: EntityManager
) : PageRepositoryCustom {
//    List<Member> result = queryFactory
//        .selectFrom(member)
//        .orderBy(member.username.desc())
//        .offset(1)
//        .limit(2)
//        .fetch();
}
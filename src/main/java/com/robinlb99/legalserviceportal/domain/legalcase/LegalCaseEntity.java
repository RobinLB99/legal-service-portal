package com.robinlb99.legalserviceportal.domain.legalcase;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinlb99.legalserviceportal.domain.client.ClientEntity;
import com.robinlb99.legalserviceportal.domain.lawyer.LawyerEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cases")
public class LegalCaseEntity implements Serializable {

    @ToString.Include
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_case")
    private Long id;

    @Column(name = "case_number", nullable = false, unique = true, length = 50)
    private String caseNumber;

    @ToString.Include
    @Column(name = "type_case", nullable = false, length = 50)
    private String typeCase;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "details", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode details;

    @Column(name = "attachments", columnDefinition = "varchar(255)[]")
    private String[] attachments;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ToString.Include
    @Column(name = "status", nullable = false, length = 50)
    private String status = "Activo";

    // @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", nullable = false)
    private ClientEntity client;

    // @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lawyer", referencedColumnName = "id_lawyer", nullable = false)
    private LawyerEntity lawyer;

}

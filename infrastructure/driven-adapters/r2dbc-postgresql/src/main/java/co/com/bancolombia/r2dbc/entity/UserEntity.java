package co.com.bancolombia.r2dbc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phone;
    @Column(unique = true)
    private String email;
    private BigDecimal salaryBase;
}

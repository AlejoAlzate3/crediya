package co.com.bancolombia.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterUserDTO(Long id, String name, String lastName, LocalDate birthDate,
                              String address, String phone, String email, BigDecimal salaryBase) {
}

package it.contrader.model;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
public class UrineTest {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;

    private Float color;
    private Float ph;
    private Float protein;
    private Float hemoglobin;
    private Long idAdmin;
    private Long idUser;
    private Boolean isChecked;
    private String dateInsert;
}

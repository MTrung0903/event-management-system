package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detail_section")
public class DetailSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="detail_section_id")
    private int id;

    @Column(name ="speaker_title")
    private String speakerTitle;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;
}

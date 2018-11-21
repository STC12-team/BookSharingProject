package ru.innopolis.stc12.booksharing.model.dao.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Type;
import ru.innopolis.stc12.booksharing.model.pojo.BookCopiesStatus;
import javax.persistence.*;

@Entity
@Table(name = "book_copies")
@TypeDef(name = "book_copies_status_enum", typeClass = PostgreSQLEnumType.class)
public class BookCopyEntity {

    public BookCopyEntity() {
    }

    private int id;
    private int book_edition_id;
    private int owner_id;
    private BookCopiesStatus status;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "book_copies_id_seq", sequenceName = "book_copies_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_copies_id_seq")
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBook_edition_id() {
        return book_edition_id;
    }

    public void setBook_edition_id(int book_edition_id) {
        this.book_edition_id = book_edition_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "public.book_copies_status")
    @Type(type = "book_copies_status_enum")
    public BookCopiesStatus getStatus() {
        return status;
    }

    public void setStatus(BookCopiesStatus status) {
        this.status = status;
    }

}

package aar;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "`user`") // From newer versions of H2, user is a reserved word and needs to be escaped
public class User implements Serializable {

   //serialVersionUID obligatorio en objetos serializables
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private int id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String profession;

   public User() {}

   public User(String name, String profession) {
      this.name = name;
      this.profession = profession;
   }

   public int getId() {
      return id;
   }

   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   @XmlElement
   public void setName(String name) {
      this.name = name;
   }
   public String getProfession() {
      return profession;
   }
   @XmlElement
   public void setProfession(String profession) {
      this.profession = profession;
   }
}

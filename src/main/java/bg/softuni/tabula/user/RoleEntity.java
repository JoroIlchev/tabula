package bg.softuni.tabula.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private long id;

  @Column(name = "role", nullable = false)
  private String role;

  public long getId() {
    return id;
  }

  public RoleEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getRole() {
    return role;
  }

  public RoleEntity setRole(String role) {
    this.role = role;
    return this;
  }

  @Override
  public String toString() {
    return "RoleEntity{" +
        "id=" + id +
        ", role='" + role + '\'' +
        '}';
  }
}

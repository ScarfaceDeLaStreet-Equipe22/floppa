package ulaval.glo2003.domain.utils;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.Objects;
import java.util.UUID;
@Entity
public class SaleStatus {
    private saleStatusPossibilities saleStatus; //
    @Id
    public String id;
    private enum  saleStatusPossibilities{
        ongoing, sold
    }

    public SaleStatus() {
        this.saleStatus = saleStatusPossibilities.ongoing;
        this.id = UUID.randomUUID().toString();
    }

    public String getStatus() {
        return saleStatus.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleStatus that = (SaleStatus) o;
        return saleStatus == that.saleStatus && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleStatus, id);
    }

    public void setSaleStatus(String saleStatus) {
        if (saleStatus == "sold"){
            this.saleStatus = saleStatusPossibilities.sold;
        } else if (saleStatus == "ongoing") {
            this.saleStatus = saleStatusPossibilities.ongoing;
        }
    }
}

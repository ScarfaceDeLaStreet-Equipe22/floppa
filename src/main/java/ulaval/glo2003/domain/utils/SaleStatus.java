package ulaval.glo2003.domain.utils;

public class SaleStatus {
    private saleStatusPossibilities saleStatus; //
    private enum  saleStatusPossibilities{
        ongoing, sold
    }

    public SaleStatus() {
        this.saleStatus = saleStatusPossibilities.ongoing;
    }

    public String getStatus() {
        return saleStatus.toString();
    }

    public void setSaleStatus(String saleStatus) {
        if (saleStatus == "sold"){
            this.saleStatus = saleStatusPossibilities.sold;
        } else if (saleStatus == "ongoing") {
            this.saleStatus = saleStatusPossibilities.ongoing;
        }
    }
}

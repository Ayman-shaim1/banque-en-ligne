package dao.Operation;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Operation {

    private int numOp;
    private Date dateOp;
    private OperationType type;
    private int numCompte;
    private double commission;


    public Operation(Date dateOp, OperationType operationType,int numCompte,double commission) {
        this.dateOp = dateOp;
        this.type = operationType;
        this.numCompte = numCompte;
        this.commission= commission;
    }

}

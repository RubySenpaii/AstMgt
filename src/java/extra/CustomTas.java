/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.util.ArrayList;
import java.util.TimerTask;
import objects.AssetIncident;
import objects.Equipment;
import services.AssetIncidentService;
import services.EquipmentService;

/**
 *
 * @author rubysenpaii
 */
public class CustomTas implements Runnable {
    @Override
    public void run() {
        try {
            // System.out.println("ii");
            ArrayList<Equipment> equipments = new EquipmentService().GetListOfEquipments();
            for (Equipment equipment: equipments) {
                AssetIncident incident = new AssetIncidentService().GetIncidentsOfAssetCount(equipment.AssetTag);
                equipment.Condition = incident.resultComparison(incident.incidentResult(), incident.usefulLifeResult(equipment.getAge(), equipment.Asset.EstimatedUsefulLife));
                new EquipmentService().UpdateEquipment(equipment);
            }
        } catch (Exception x) {
            System.out.println(x);
        }
    }
}

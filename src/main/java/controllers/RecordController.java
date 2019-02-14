/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Record;

/**
 *
 * @author nikolaos
 */
public class RecordController {

    private Record record;
    private List<Record> recordList;

    public RecordController() {
        initRecords();
    }

    public RecordController(Record record, List<Record> recordList) {
        this.record = record;
        this.recordList = recordList;
    }

    public void addRecord(Record record){
        this.recordList.add(record);
    }
    
    public List<Record> findAll(){
        return this.recordList;
    }
    
    public Record findRecordById(int id){
        Record ans = new Record();
        for(Record record: this.recordList){
            if(record.getId() == id){
                ans = record;
                break;
            }
        }
        return ans;
    }
    
    public void updateRecord(Record record){
        System.out.println("UPDATING " + record);
        for(Record rec: this.recordList){
            if(rec.getId() == record.getId()){
                rec.setName(record.getName());
                rec.setPhone(record.getPhone());
                rec.setGender(record.getGender());
                break;
            }
        }
        
    }
    
    public void deleteRecord(Record record){
        this.recordList.remove(record);
    }
    
    private void initRecords() {
        List<Record> lst = new ArrayList();
        lst.add(new Record(false, 1, "nikolaos", "12345678", 'm'));
        lst.add(new Record(true, 2, "maria", "24524322345", 'f'));
        lst.add(new Record(false, 3, "kostas", "654345364", 'm'));
        lst.add(new Record(true, 4, "giorgos", "43245455", 'm'));
        lst.add(new Record(false, 5, "paraskevi", "76434643", 'f'));
        lst.add(new Record(false, 6, "aggeliki", "345345234", 'f'));
        this.recordList = lst;
    }
}

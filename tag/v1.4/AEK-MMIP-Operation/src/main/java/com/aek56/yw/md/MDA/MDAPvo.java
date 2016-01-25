package com.aek56.yw.md.MDA;

import java.util.List;

import javax.inject.Named;

import com.aek56.atm.master.MDA_CPXXX.MDA_CPXXXDBO;
import com.aek56.atm.master.MDB_CPXFLXX.MDB_CPXFLXXDBO;

@Named
public class MDAPvo extends MDA_CPXXXDBO{
    private List<MDB_CPXFLXXDBO> cpxlbList;

    public List<MDB_CPXFLXXDBO> getCpxlbList() {
        return cpxlbList;
    }

    public void setCpxlbList(List<MDB_CPXFLXXDBO> cpxlbList) {
        this.cpxlbList = cpxlbList;
    }
    
}

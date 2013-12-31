package cn.edu.nju.software.dochub.service;

import cn.edu.nju.software.dochub.data.dao.ReferenceTypeDAO;
import cn.edu.nju.software.dochub.data.dataobject.ReferenceType;

import java.util.List;

/**
 * Created by tym on 13-12-31.
 */
public class ReferenceTypeService {

    private ReferenceTypeDAO referenceTypeDAO;

    public List<ReferenceType> getAllReferenceTypes() {
        return referenceTypeDAO.findAll();
    }

    public void addReferenceType(ReferenceType referenceType) {
        if (referenceTypeDAO.findByName(referenceType.getName()).size() == 0) {
            referenceTypeDAO.save(referenceType);
        }
    }

    public void deleteReferenceType(ReferenceType referenceType) {
        referenceTypeDAO.delete(referenceType);
    }

    public void deleteReferenceTypeById(int id) {
        ReferenceType referenceType = referenceTypeDAO.findById(id);
        deleteReferenceType(referenceType);
    }

    public void setReferenceTypeDAO(ReferenceTypeDAO referenceTypeDAO) {
        this.referenceTypeDAO = referenceTypeDAO;
    }
}

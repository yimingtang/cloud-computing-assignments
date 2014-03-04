package cn.edu.nju.software.dochub.service;

import cn.edu.nju.software.dochub.data.dao.ReferenceDAO;
import cn.edu.nju.software.dochub.data.dao.ReferenceTypeDAO;
import cn.edu.nju.software.dochub.data.dataobject.Document;
import cn.edu.nju.software.dochub.data.dataobject.Reference;
import cn.edu.nju.software.dochub.data.dataobject.ReferenceType;

import java.util.List;

/**
 * Created by tym on 13-12-31.
 */
public class ReferenceService {

    ReferenceTypeDAO referenceTypeDAO;
    ReferenceDAO referenceDAO;

    public List<ReferenceType> getAllReferenceTypes() {
        return referenceTypeDAO.findAll();
    }

    public void addReferenceType(ReferenceType referenceType) {
        if (referenceTypeDAO.findByName(referenceType.getName()).size() == 0) {
            referenceTypeDAO.save(referenceType);
        }
    }

    public ReferenceType getReferenceTypeByDocs(Document src, Document dest) {
        List<Reference> referenceList = referenceDAO.findByDocs(src, dest);
        if (referenceList.size() == 0) {
            return null;
        } else {
            return referenceList.get(0).getReferenceType();
        }
    }

    public ReferenceType getReferenceTypeByName(String name) {
        return referenceTypeDAO.findByName(name).get(0);
    }

    public Reference getReferenceByDocs(Document src, Document dest) {
        List<Reference> referenceList = referenceDAO.findByDocs(src, dest);
        if (referenceList.size() == 0) {
            return null;
        } else {
            return referenceList.get(0);
        }
    }

    public void updateReference(Reference reference) {
        referenceDAO.merge(reference);
    }

    public void addReference(Document source, Document dest, ReferenceType referenceType) {
        Reference reference = new Reference();
        reference.setDocumentBySource(source);
        reference.setDocumentByDestination(dest);
        reference.setReferenceType(referenceType);
        referenceDAO.save(reference);
    }

    public void deleteReference(Document source, Document dest) {
        Reference reference = getReferenceByDocs(source, dest);
        referenceDAO.delete(reference);
    }

    public void deleteReferenceType(ReferenceType referenceType) {
        referenceTypeDAO.delete(referenceType);
    }

    public void deleteReferenceTypeById(int id) {
        ReferenceType referenceType = referenceTypeDAO.findById(id);
        deleteReferenceType(referenceType);
    }

    public void deleteReferenceById(int id) {
        Reference reference = referenceDAO.findById(id);
        referenceDAO.delete(reference);
    }


    public void setReferenceTypeDAO(ReferenceTypeDAO referenceTypeDAO) {
        this.referenceTypeDAO = referenceTypeDAO;
    }

    public void setReferenceDAO(ReferenceDAO referenceDAO) {
        this.referenceDAO = referenceDAO;
    }
}

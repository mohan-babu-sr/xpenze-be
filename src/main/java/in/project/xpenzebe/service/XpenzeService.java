package in.project.xpenzebe.service;

import in.project.xpenzebe.exception.XpenzeCollectionException;
import in.project.xpenzebe.model.Xpenze;

import java.util.List;

public interface XpenzeService {
    public List<Xpenze> getAllXpenze();

    public Xpenze getSingleXpenze(String id) throws XpenzeCollectionException;

    public void createXpenze(Xpenze xpenze) throws XpenzeCollectionException;

    public void updateXpenze(String id, Xpenze xpenze) throws XpenzeCollectionException;

    public void deleteXpenzeById(String id) throws XpenzeCollectionException;

}

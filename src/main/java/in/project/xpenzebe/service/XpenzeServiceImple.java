package in.project.xpenzebe.service;

import in.project.xpenzebe.exception.XpenzeCollectionException;
import in.project.xpenzebe.model.Xpenze;
import in.project.xpenzebe.repository.XpenzeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class XpenzeServiceImple implements XpenzeService {

    @Autowired
    private XpenzeRepository xpenzeRepo;

    @Override
    public List<Xpenze> getAllXpenze() {
        List<Xpenze> xpenze = xpenzeRepo.findAll();
        if (xpenze.size() > 0) {
            return xpenze;
        }else {
            return new ArrayList<Xpenze>();
        }
    }

    @Override
    public Xpenze getSingleXpenze(String id) throws XpenzeCollectionException {
        Optional<Xpenze> xpenzeOptional = xpenzeRepo.findById(id);
        if (!xpenzeOptional.isPresent()) {
            throw new XpenzeCollectionException(XpenzeCollectionException.NotFoundException(id));
        }else {
            return xpenzeOptional.get();
        }
    }

    @Override
    public void createXpenze(Xpenze xpenze) throws XpenzeCollectionException {
        Optional<Xpenze> xpenzeOptional= xpenzeRepo.findByXpenze(xpenze.getXpenze());
        if(xpenzeOptional.isPresent())
        {
            throw new XpenzeCollectionException(XpenzeCollectionException.XpenzeAlreadyExists());
        }
        else
        {
            xpenze.setCreatedAt(new Date(System.currentTimeMillis()));
            xpenzeRepo.save(xpenze);
        }
    }

    @Override
    public void updateXpenze(String id, Xpenze xpenze) throws XpenzeCollectionException {
        Optional<Xpenze> xpenzeWithId = xpenzeRepo.findById(id);
        Optional<Xpenze> xpenzeWithSameName = xpenzeRepo.findByXpenze(xpenze.getXpenze());
        if(xpenzeWithId.isPresent())
        {
            if(xpenzeWithSameName.isPresent() && !xpenzeWithSameName.get().getId().equals(id))
            {

                throw new XpenzeCollectionException(XpenzeCollectionException.XpenzeAlreadyExists());
            }
            Xpenze xpenzeToUpdate=xpenzeWithId.get();

            xpenzeToUpdate.setXpenze(xpenze.getXpenze());
            xpenzeToUpdate.setDescription(xpenze.getDescription());
            xpenzeToUpdate.setCompleted(xpenze.getCompleted());
            xpenzeToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            xpenzeRepo.save(xpenzeToUpdate);
        }
        else
        {
            throw new XpenzeCollectionException(XpenzeCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteXpenzeById(String id) throws XpenzeCollectionException {
        Optional<Xpenze> xpenzeOptional = xpenzeRepo.findById(id);
        if(!xpenzeOptional.isPresent())
        {
            throw new XpenzeCollectionException(XpenzeCollectionException.NotFoundException(id));
        }
        else
        {
            xpenzeRepo.deleteById(id);
        }
    }
}

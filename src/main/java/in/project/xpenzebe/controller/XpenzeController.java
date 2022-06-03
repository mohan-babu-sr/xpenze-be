package in.project.xpenzebe.controller;

import in.project.xpenzebe.exception.XpenzeCollectionException;
import in.project.xpenzebe.model.Xpenze;
import in.project.xpenzebe.service.XpenzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;


@RestController
@CrossOrigin("*")
public class XpenzeController {
    @Autowired
    private XpenzeService xpenzeService;

    @PostMapping("/xpenze")
    public ResponseEntity<?> createXpenze(@RequestBody Xpenze xpenze) {
        try {
            xpenzeService.createXpenze(xpenze);
            return new ResponseEntity<Xpenze>(xpenze, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (XpenzeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/xpenze")
    public ResponseEntity<?> getAllXpenze() {
        List<Xpenze> xpenze = xpenzeService.getAllXpenze();
        return new ResponseEntity<>(xpenze, xpenze.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/xpenze/{id}")
    public ResponseEntity<?> getSingleXpenze(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(xpenzeService.getSingleXpenze(id), HttpStatus.OK);
        } catch (XpenzeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/xpenze/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws XpenzeCollectionException{
        try{
            xpenzeService.deleteXpenzeById(id);
            return new ResponseEntity<>("Successfully deleted xpenze with id "+id, HttpStatus.OK);
        }
        catch (XpenzeCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/xpenze/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Xpenze xpenze)
    {
        try {
            xpenzeService.updateXpenze(id,xpenze);
            return new ResponseEntity<>("Updated movie with id "+id+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (XpenzeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

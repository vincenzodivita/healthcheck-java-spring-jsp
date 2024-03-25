package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.RegistryDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.BloodTestService;
import it.contrader.service.EmailSenderService;
import it.contrader.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/bloodTest")
public class BloodTestController {

    @Autowired
    private BloodTestService service;

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private RegistryService registryService;

    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        return "/bloodTest/bloodTest";
    }
    @GetMapping("/getalladmin")
    public String getAllAdmin(HttpServletRequest request) {
        setAllAdmin(request);
        return "/bloodTest/bloodTest";
    }

    @GetMapping("/getalluser")
    public String getAllUser(HttpServletRequest request) {
        setAllUser(request);
        return "/bloodTest/bloodTest";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        service.delete(id);

        if(user.getUsertype().toString().equals("USER")){
            setAllUser(request);
        } else if (user.getUsertype().toString().equals("ADMIN")){
            setAllAdmin(request);
        } else if (user.getUsertype().toString().equals("SUPER")) {
            setAll(request);
        }
        return "/bloodTest/bloodTest";
    }
    @GetMapping("/preupdate")     public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
        request.getSession().setAttribute("dto", service.read(id));
        return "/bloodTest/updateBloodTest";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("redBloodCell") Float redBloodCell, @RequestParam("whiteBloodCell") Float whiteBloodCell, @RequestParam("platelets") Float platelets, @RequestParam("hemoglobin") Float hemoglobin, @RequestParam("idAdmin") Long idAdmin, @RequestParam("idUser") Long idUser, @RequestParam("isChecked") Boolean isChecked, @RequestParam("dateInsert") String dateInsert) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        BloodTestDTO dto = new BloodTestDTO();
        dto.setId(id);
        dto.setRedBloodCell(redBloodCell);
        dto.setWhiteBloodCell(whiteBloodCell);
        dto.setPlatelets(platelets);
        dto.setHemoglobin(hemoglobin);
        dto.setIdAdmin(idAdmin);
        dto.setIdUser(idUser);
        dto.setIsChecked(isChecked);
        dto.setDateInsert(dateInsert);

        service.update(dto);

        if(user.getUsertype().toString().equals("USER")){
            setAllUser(request);
       } else if(user.getUsertype().toString().equals("ADMIN")) {
            setAllAdmin(request);
        } else {
            setAll(request);
        }
        return "/bloodTest/bloodTest";

    }
    @PostMapping("/check")
    public String check(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("redBloodCell") Float redBloodCell, @RequestParam("whiteBloodCell") Float whiteBloodCell, @RequestParam("platelets") Float platelets, @RequestParam("hemoglobin") Float hemoglobin, @RequestParam("idAdmin") Long idAdmin, @RequestParam("idUser") Long idUser, @RequestParam("isChecked") Boolean isChecked, @RequestParam("dateInsert") String dateInsert) {
        UserDTO userSession = (UserDTO) request.getSession().getAttribute("user");
        BloodTestDTO dto = new BloodTestDTO();
        dto.setId(id);
        dto.setRedBloodCell(redBloodCell);
        dto.setWhiteBloodCell(whiteBloodCell);
        dto.setPlatelets(platelets);
        dto.setHemoglobin(hemoglobin);
        dto.setIdAdmin(idAdmin);
        dto.setIdUser(idUser);
        dto.setIsChecked(isChecked);
        dto.setDateInsert(dateInsert);
        service.update(dto);
        if(userSession.getUsertype().toString().equals("ADMIN")){
            setAllAdmin(request);
        } else {
            setAll(request);
        }
        return "/bloodTest/bloodTest";

    }

    @PostMapping("/insert")
    public String update(HttpServletRequest request,
                         @RequestParam("redBloodCell") Float redBloodCell, @RequestParam("whiteBloodCell") Float whiteBloodCell,
                         @RequestParam("platelets") Float platelets, @RequestParam("hemoglobin") Float hemoglobin,
                         @RequestParam("idAdmin") Long idAdmin, @RequestParam("idUser") Long idUser,
                         @RequestParam("isChecked") Boolean isChecked, @RequestParam("dateInsert") String dateInsert)  {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        BloodTestDTO dto = new BloodTestDTO();
        dto.setRedBloodCell(redBloodCell);
        dto.setWhiteBloodCell(whiteBloodCell);
        dto.setPlatelets(platelets);
        dto.setHemoglobin(hemoglobin);
        dto.setIdAdmin(idAdmin);
        dto.setIdUser(idUser);
        dto.setIsChecked(isChecked);
        dto.setDateInsert(dateInsert);
        service.insert(dto);

        List<String> referto = new ArrayList<>();
        referto.add("Globuli Rossi: " + redBloodCell);
        referto.add("Globuli Bianchi: " + whiteBloodCell);
        referto.add("Piastrine: " + platelets);
        referto.add("Emoglobina: " + hemoglobin);
        referto.add("Data: " + dateInsert);

        senderService.sendEmail(user.getEmail(), "Analisi del sangue", referto);
        if(user.getUsertype().toString().equals("USER")){
            setAllUser(request);
        } else {
            setAll(request);
        }

        return "/bloodTest/bloodTest";
    }

    @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("id") Long id) {
        BloodTestDTO bloodTest = service.read(id);
        request.getSession().setAttribute("dto", bloodTest);
        RegistryDTO paziente = registryService.readByUser(bloodTest.getIdUser());
        request.getSession().setAttribute("paziente", paziente);
        RegistryDTO dottore = registryService.readByUser(bloodTest.getIdAdmin());
        request.getSession().setAttribute("dottore", dottore);
        return "/bloodTest/readBloodTest";
    }



    @GetMapping("/searchUser")
    public String getAllSearchUser(HttpServletRequest request,@RequestParam("year") String year, @RequestParam("month") String month) {
        setAllSearchUser(request, year, month);

        return "/bloodTest/bloodTest";
    }
    @GetMapping("/searchAdmin")
    public String getAllSearchAdmin(HttpServletRequest request,@RequestParam("cf") String cf) {
        setAllSearchAdmin(request,cf.toUpperCase());

        return "/bloodTest/bloodTest";
    }

    @GetMapping("/searchSuper")
    public String getAllSearchSuper(HttpServletRequest request,@RequestParam("cf") String cf) {
        setAllSearchSuper(request,cf.toUpperCase());

        return "/bloodTest/bloodTest";
    }


    private void setAll(HttpServletRequest request) {
        List<BloodTestDTO> bloodTest = service.getAll();
        List<RegistryDTO> allPazienti = new ArrayList<>();
        List<RegistryDTO> allDottori = new ArrayList<>();

        for (BloodTestDTO b : bloodTest){
            RegistryDTO paziente = registryService.readByUser(b.getIdUser());
            allPazienti.add(paziente);

            RegistryDTO dottore = registryService.readByUser(b.getIdAdmin());
            allDottori.add(dottore);
        }

        request.getSession().setAttribute("list", bloodTest);
        request.getSession().setAttribute("pazienti", allPazienti);
        request.getSession().setAttribute("dottori", allDottori);
    }


    private void setAllAdmin(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<BloodTestDTO> bloodTest = service.findByIdAdmin(user.getId());
        List<RegistryDTO> allPazienti = new ArrayList<>();

        for (BloodTestDTO b : bloodTest){
            RegistryDTO paziente = registryService.readByUser(b.getIdUser());
            allPazienti.add(paziente);
        }
        request.getSession().setAttribute("list", bloodTest);
        request.getSession().setAttribute("pazienti", allPazienti);
    }

    private void setAllUser(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<BloodTestDTO> bloodTest = service.findByIdUser(user.getId());
        List<RegistryDTO> allDottori = new ArrayList<>();

        for (BloodTestDTO b : bloodTest){
            RegistryDTO paziente = registryService.readByUser(b.getIdAdmin());
            allDottori.add(paziente);
        }
        request.getSession().setAttribute("list", bloodTest);
        request.getSession().setAttribute("dottori", allDottori);
    }


    private void setAllSearch(HttpServletRequest request, String year, String month) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        request.getSession().setAttribute("list", service.findAllByYearMonthAndUserId(year, month , user.getId()));
    }


    private void setAllSearchUser(HttpServletRequest request, String year, String month) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        request.getSession().setAttribute("list", service.findAllByYearMonthAndUserId(year, month , user.getId()));
    }

    private void setAllSearchAdmin(HttpServletRequest request, String cf) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        request.getSession().setAttribute("list", service.findAllByCf(user.getId(), cf));
    }

    private void setAllSearchSuper(HttpServletRequest request, String cf) {
        request.getSession().setAttribute("list", service.findAllByCf(cf));
    }

}

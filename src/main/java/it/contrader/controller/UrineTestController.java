package it.contrader.controller;


import it.contrader.dto.*;
import it.contrader.dto.UrineTestDTO;
import it.contrader.model.User;
import it.contrader.service.EmailSenderService;
import it.contrader.service.RegistryService;
import it.contrader.service.UrineTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.persistence.NamedQuery;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/urinetest")
public class UrineTestController {

    @Autowired
    private UrineTestService service;

    @Autowired
    private EmailSenderService senderService;
    @Autowired
    private RegistryService registryService;

    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        return "/urineTest/urinetestmanager";
    }

    @GetMapping("/getalluser")
    public String getAllUser(HttpServletRequest request) {
        setAllUser(request);
        return "/urineTest/urinetestmanager";
    }
    @GetMapping("/getalladmin")
    public String getAllAdmin(HttpServletRequest request){
        setAllAdmin(request);
        return "/urineTest/urinetestmanager";
    }

    @GetMapping("/searchUser")
    public String getAllSearchUser(HttpServletRequest request,@RequestParam("year") String year, @RequestParam("month") String month) {
            setAllSearchUser(request, year, month);

        return "/urineTest/urinetestmanager";
    }

    @GetMapping("/searchAdmin")
    public String getAllSearchAdmin(HttpServletRequest request,@RequestParam("cf") String cf) {
        setAllSearchAdmin(request,cf.toUpperCase());

        return "/urineTest/urinetestmanager";
    }

    @GetMapping("/searchSuper")
    public String getAllSearchSuper(HttpServletRequest request,@RequestParam("cf") String cf) {
        setAllSearchSuper(request,cf.toUpperCase());

        return "/urineTest/urinetestmanager";
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
        return "/urineTest/urinetestmanager";
    }
    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
        request.getSession().setAttribute("dto", service.read(id));
        return "/urineTest/updateurinetest";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("color") Float color,
                         @RequestParam("ph") Float ph, @RequestParam("protein") Float protein, @RequestParam("hemoglobin") Float hemoglobin,
                         @RequestParam("idAdmin") Long idAdmin, @RequestParam("idUser") Long idUser, @RequestParam("isChecked") Boolean isChecked,
                         @RequestParam("dateInsert") String dateInsert) {

        UserDTO userSession = (UserDTO) request.getSession().getAttribute("user");
        UrineTestDTO dto = new UrineTestDTO();
        dto.setId(id);
        dto.setColor(color);
        dto.setPh(ph);
        dto.setProtein(protein);
        dto.setHemoglobin(hemoglobin);
        dto.setIdAdmin(idAdmin);
        dto.setIdUser(idUser);
        dto.setIsChecked(isChecked);
        dto.setDateInsert(dateInsert);

        service.update(dto);

        if(userSession.getUsertype().toString().equals("USER")){
            setAllUser(request);
        } else if (userSession.getUsertype().toString().equals("ADMIN")){
            setAllAdmin(request);
        } else if (userSession.getUsertype().toString().equals("SUPER"))
        {
            setAll(request);
        }
        return "/urineTest/urinetestmanager";

    }

    @PostMapping("/insert")
    public String insert(HttpServletRequest request, @RequestParam("color") Float color,
                         @RequestParam("ph") Float ph, @RequestParam("protein") Float protein,
                         @RequestParam("hemoglobin") Float hemoglobin, @RequestParam("idAdmin") Long idAdmin,
                         @RequestParam("idUser") Long idUser, @RequestParam("isChecked") Boolean isChecked,
                         @RequestParam("dateInsert") String dateInsert) {

        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        UrineTestDTO dto = new UrineTestDTO();
        dto.setColor(color);
        dto.setPh(ph);
        dto.setProtein(protein);
        dto.setHemoglobin(hemoglobin);
        dto.setIdAdmin(idAdmin);
        dto.setIdUser(idUser);
        dto.setDateInsert(dateInsert);
        dto.setIsChecked(isChecked);
        service.insert(dto);

        List<String> referto = new ArrayList<>();
        referto.add("Colore: " + color);
        referto.add("Ph: " + ph);
        referto.add("Proteine: " + protein);
        referto.add("Emoglobina: " + hemoglobin);
        referto.add("Data: " + dateInsert);

        senderService.sendEmail(user.getEmail(), "Analisi delle urine", referto);

        if(user.getUsertype().toString().equals("USER")){
            setAllUser(request);
        } else {
            setAll(request);
        }
        return "/urineTest/urinetestmanager";
    }

    @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("id") Long id) {
        UrineTestDTO urineTest = service.read(id);
        request.getSession().setAttribute("dto", urineTest);
        RegistryDTO paziente = registryService.readByUser(urineTest.getIdUser());
        request.getSession().setAttribute("paziente", paziente);
        RegistryDTO dottore = registryService.readByUser(urineTest.getIdAdmin());
        request.getSession().setAttribute("dottore", dottore);
        return "/urineTest/readurinetest";
    }

    @PostMapping("/check")
    public String check(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("color") Float color,
                        @RequestParam("ph") Float ph, @RequestParam("protein") Float protein, @RequestParam("hemoglobin") Float hemoglobin,
                        @RequestParam("idAdmin") Long idAdmin, @RequestParam("idUser") Long idUser, @RequestParam("isChecked") Boolean isChecked,
                        @RequestParam("dateInsert") String dateInsert) {

        UserDTO userSession = (UserDTO) request.getSession().getAttribute("user");
        UrineTestDTO dto = new UrineTestDTO();
        dto.setId(id);
        dto.setColor(color);
        dto.setPh(ph);
        dto.setProtein(protein);
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
        return "/urineTest/urinetestmanager";

    }

    private void setAll(HttpServletRequest request) {

        List<UrineTestDTO> urineTest = service.getAll();
        List<RegistryDTO> allPazienti = new ArrayList<>();
        List<RegistryDTO> allDottori = new ArrayList<>();

        for (UrineTestDTO u : urineTest){
            RegistryDTO paziente = registryService.readByUser(u.getIdUser());
            allPazienti.add(paziente);

            RegistryDTO dottore = registryService.readByUser(u.getIdAdmin());
            allDottori.add(dottore);
        }

        request.getSession().setAttribute("list", urineTest);
        request.getSession().setAttribute("pazienti", allPazienti);
        request.getSession().setAttribute("dottori", allDottori);
    }

    private void setAllUser(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<UrineTestDTO> urinetest = service.findByIdUser(user.getId());
        List<RegistryDTO> allDottori = new ArrayList<>();

        for (UrineTestDTO u : urinetest){
            RegistryDTO paziente = registryService.readByUser(u.getIdAdmin());
            allDottori.add(paziente);
        }
        request.getSession().setAttribute("list", urinetest);
        request.getSession().setAttribute("dottori", allDottori);
    }
    private void setAllAdmin(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<UrineTestDTO> urinetest = service.findByAllIdAdmin(user.getId());
        List<RegistryDTO> allPazienti = new ArrayList<>();

        for (UrineTestDTO u : urinetest){
            RegistryDTO paziente = registryService.readByUser(u.getIdUser());
            allPazienti.add(paziente);
        }
        request.getSession().setAttribute("list", urinetest);
        request.getSession().setAttribute("pazienti", allPazienti);
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
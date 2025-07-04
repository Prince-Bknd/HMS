package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import models.Format;
import models.Medicine;
import models.MedicineDenomination;
import models.MedicineFormat;
import models.PharmaCompany;
import models.Unit;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet({"/save_medicine.do", "/update_medicine.do", "/get_medicine_formats.do", "/save_medicine_formats.do", "/add_denomination.do", "/update_denomination.do"})
public class PharmacyServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        HttpSession session = request.getSession();
        if(uri.equals("/get_medicine_formats.do")){
            String responseFromServer = "expired";
            PharmaCompany pharmaCompany = (PharmaCompany)session.getAttribute("pharmaCompany");

            if (pharmaCompany != null) {
                int id = Integer.parseInt(request.getParameter("medicineId"));
                MedicineFormat medicineFormat = new MedicineFormat(new Medicine(id));
            
                ArrayList<Integer> medicineFormats = medicineFormat.getAllMedicineFormats();
            
                Gson gson = new Gson();
                responseFromServer = gson.toJson(medicineFormats);
            }
            

            response.getWriter().write(responseFromServer);
            return;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        HttpSession session = request.getSession();
        if(uri.equals("/save_medicine.do")){
            String res = "login";
            PrintWriter out = response.getWriter();
            PharmaCompany pharmaCompany = (PharmaCompany)session.getAttribute("pharmaCompany");
            if(pharmaCompany != null){
                response.setContentType("text/plain");
                String name = null;
                String description = null;
                String ingredients = null;
                String effectiveBodyPart = null;
                String sideEffects = null;
                String warnings = null;
                String pic = null;
                ArrayList<MedicineFormat> medicineFormats = new ArrayList<>();

                if(ServletFileUpload.isMultipartContent(request)){
                    try{
                        List<FileItem> fis = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                        for(FileItem fi : fis){
                            if(fi.isFormField()){
                                String fname = fi.getFieldName();
                                String fvalue = fi.getString();
                                switch(fname){
                                    case "name":
                                        name = fvalue;
                                        break;
                                    case "description":
                                        description = fvalue;
                                        break;
                                    case "ingredients":
                                        ingredients = fvalue;
                                        break;
                                    case "effectiveBodyPart":
                                        effectiveBodyPart = fvalue;
                                        break;
                                    case "sideEffects":
                                        sideEffects = fvalue;
                                        break;
                                    case "warnings":
                                        warnings = fvalue;
                                        break;
                                    case "formats":
                                        //=========================================================
                                        medicineFormats.add(new MedicineFormat(new Format(Integer.parseInt(fvalue)))); 
                                        //=========================================================
                                        break; 
                                }
                            } else{
                                String path = getServletContext().getRealPath("/WEB-INF/uploads/" + pharmaCompany.getUser().getUserId() + "/medicines");
                                String fileName = new java.util.Date().getTime() + fi.getName();
                                File pth = new File(path);
                                if(!pth.exists()){
                                    pth.mkdirs();
                                }
                                pic = "medicines/" + fileName;
                                File file = new File(pth, fileName);
                                try{
                                    fi.write(file);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch(FileUploadException e){
                        e.printStackTrace();
                    }
                }

                Medicine medicine = new Medicine(name, description, ingredients, effectiveBodyPart, sideEffects, warnings, pic, pharmaCompany, medicineFormats);
                if(medicine.insertData(pharmaCompany)){
                    res = "done";
                } else{
                    res = "!inserted";
                }           
                out.write(res);
                return;
            } else{
                out.write(res);
                return;
            }
        } else if(uri.equals("/update_medicine.do")){
            PharmaCompany pharmaCompany = (PharmaCompany)session.getAttribute("pharmaCompany");
            String responseFromServer = "expired";
            if(pharmaCompany != null){
                responseFromServer = "Ready to update medicine";

                Medicine medicine = new Medicine(Integer.parseInt(request.getParameter("medicineId")), request.getParameter("MedName"), request.getParameter("medicineIngredients"), request.getParameter("medicineEffectiveBodypart"), request.getParameter("medicineSideEffects"), request.getParameter("medicineDescription"), request.getParameter("medicineWarnings"));
                if(medicine.updateMedicine()){
                    responseFromServer = "saved";
                }
            }

            response.getWriter().write(responseFromServer);
            return;
        } else if(uri.equals("/save_medicine_formats.do")){
            PharmaCompany pharmaCompany = (PharmaCompany)session.getAttribute("pharmaCompany");
            String responseFromServer = "expired";
            if(pharmaCompany != null){
                String selectedFormats = request.getParameter("selectedFormats");
                Integer medicineId = Integer.parseInt(request.getParameter("medicineId"));
                if (selectedFormats != null && !selectedFormats.isEmpty()) {
                    String[] formatIds = selectedFormats.split(",");
                    MedicineFormat medicineFormat = new MedicineFormat();
                    if(medicineFormat.insertUpdatedFormats(medicineId, formatIds)){
                        responseFromServer = "saved";
                    } else{
                        responseFromServer = "Can't Save";
                    }
                } else{
                    responseFromServer = "Select";    
                }
            }

            response.getWriter().write(responseFromServer);
            return;
        } else if(uri.equals("/add_denomination.do")){
            PharmaCompany pharmaCompany = (PharmaCompany)session.getAttribute("pharmaCompany");
            String responseFromServer = "expired";

            if(pharmaCompany != null){
                MedicineFormat medicineFormat = new MedicineFormat(new Medicine(
                                                                    Integer.parseInt(request.getParameter("medicineId"))
                                                                    ), 
                                                                    new Format(
                                                                        Integer.parseInt(request.getParameter("format"))
                                                                    )
                                                                );

                Integer medicineFormatId = medicineFormat.saveMedicineFormatId();

                MedicineDenomination medicineDenomination = new MedicineDenomination(
                                                                Integer.parseInt(request.getParameter("quantity")),
                                                                new MedicineFormat(
                                                                    medicineFormatId
                                                                ),
                                                                new Unit(
                                                                    Integer.parseInt(request.getParameter("unit"))
                                                                )
                                                            );
                // System.out.println(Integer.parseInt(request.getParameter("quantity")) + "~" + medicineFormatId + "~" + Integer.parseInt(request.getParameter("unit")));
                
                if(medicineDenomination.insertData()){
                    responseFromServer = "done";
                } else{
                    responseFromServer = "error while Inserting in database";
                }

            }

            response.getWriter().write(responseFromServer);
            return;
        } else if(uri.equals("/update_denomination.do")){
            PharmaCompany pharmaCompany = (PharmaCompany)session.getAttribute("pharmaCompany");
            String responseFromServer = "expired";

            if(pharmaCompany != null){
                String jsonString = request.getParameter("denominations");

                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<MedicineDenomination>>() {}.getType();
                ArrayList<MedicineDenomination> denominations = gson.fromJson(jsonString, listType);

                MedicineDenomination medicineDenomination = new MedicineDenomination();
                if(medicineDenomination.insertDenominations(denominations)){
                    responseFromServer = "done";
                } else{
                    responseFromServer = "error while inserting denominations";
                }
            }

            response.getWriter().write(responseFromServer);
            return;
        }
        
    }
    
}

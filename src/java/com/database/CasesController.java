package com.database;

import com.database.util.JsfUtil;
import com.database.util.JsfUtil.PersistAction;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter; 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name="casesController")
@ViewScoped
public class CasesController implements Serializable {

    @EJB
    private com.database.CasesFacade ejbFacade;
    
    @EJB
    private com.database.WitnessFacade ejbWitnessFacade;
    
    private List<Cases> items = null;
    private Cases selected;

    public CasesController() {
    }
     
    
    public Cases getSelected() {
        return selected;
    }

    public void setSelected(Cases selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CasesFacade getFacade() {
        return ejbFacade;
    }

    public Cases prepareCreate() {
        selected = new Cases();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void addWitness(){
         for(Witness w : witnesses){
            Witness wit = new Witness();
            wit=w;
            System.out.println(w.getFullname()+" : "+selected.getId());
            wit.setOnecase(selected);
            ejbWitnessFacade.create(wit);
        } 
    }
    public void create() {
        //Give number 1 by default
        selected.setCaseStatus(1);  
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CasesCreated"));
        addWitness();
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
       
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CasesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CasesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cases> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected); 
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Cases getCases(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Cases> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cases> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Cases.class)
    public static class CasesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CasesController controller = (CasesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "casesController");
            return controller.getCases(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cases) {
                Cases o = (Cases) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cases.class.getName()});
                return null;
            }
        }

    }

    
    //Add Rows Dynamically  
    private List<Witness> witnesses; // private List<Item> items;
    
    
    @PostConstruct
    public void init() {
        witnesses = new ArrayList<Witness>();
    }
    
    public void add() { 
        System.out.println("Try to add");
        Witness witnes=new Witness();//Item item = new Item();
        witnesses.add(witnes); 
    }

    public void remove(Witness witnes) {
        System.out.println("Try to remove");
        witnesses.remove(witnes);
    }

    public List<Witness> getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(List<Witness> witnesses) {
        this.witnesses = witnesses;
    }

    public int countCasesByID(int num){
        List<Cases> cases = null;
        cases = ejbFacade.findCasebyId(num);
        return cases.size();
    }
    
    public int countImportantCasesByID(String level){
        List<Cases> cases = null;
        cases = ejbFacade.findImportantCasebyId(level);
        return cases.size();
    } 
    
    public List<Witness> findByCaseId(int caseId) {
        
        List<Witness> wit = null;
        wit = ejbWitnessFacade.findByCaseId(caseId);
        return wit;
    }
    // Print PDF, ... , ...... using Jasper report
    public void printPDF() throws JRException, IOException{
        String fileName = "cases.pdf";
        String jasperPath = "/resources/cases.jasper";
        this.PDF(null, jasperPath, items, fileName);
    }
    public void PDF(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException{
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
        HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        //response.addHeader("content-disposition", "attachment;filename="+fileName);
        ServletOutputStream stream = response.getOutputStream(); 
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }
     
}
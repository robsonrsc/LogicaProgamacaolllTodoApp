
package model;

import java.util.Date;

/**
 *
 * @author robson
 */
public class task {
    
    
    private int id;
    private int idProject;
    private String Name;
    private String description;
    private String notes;
    private boolean isCompleted;
    private Date deadline;
    private Date createdAt;
    private Date upDateAt;

    public task(int id, int idProject, String Name, 
            String description, String notes, boolean isCompleted, 
            Date deadline, Date createdAt, Date upDateAt) {
        
        this.id = id;
        this.idProject = idProject;
        this.Name = Name;
        this.description = description;
        this.notes = notes;
        this.isCompleted = isCompleted;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.upDateAt = upDateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getDeadline() {
        return deadline;
    }


    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpDateAt() {
        return upDateAt;
    }

    public void setUpDateAt(Date upDateAt) {
        this.upDateAt = upDateAt;
    }

    @Override
    public String toString() {
        return "task{" + "id=" + id + ", idProject=" + idProject + ", Name=" + Name + ", description=" + description + ", notes=" + notes + ", isCompleted=" + isCompleted + ", deadline=" + deadline + ", createdAt=" + createdAt + ", upDateAt=" + upDateAt + '}';
    }
    
    public task (){
        this.createdAt = new Date();
        this.upDateAt = new Date();
        this.deadline= new Date();
    }
        
}

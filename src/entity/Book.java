package entity;

public class Book {
    private int isbn;
    private String title;
    private String author;
    private int edition;
    private String gender;
    private int stock;
    private double price;

    public int getIsbn(){
        return isbn; 
    }
    public void setIsbn(int isbn){ 
        this.isbn = isbn; 
    }

    public String getTitle(){ 
        return title; 
    }
    public void setTitle(String title){ 
        this.title = title;
    }

    public String getAuthor(){ 
        return author;
    }

    public void setAuthor(String author){ 
        this.author = author; 
    }

    public int getEdition(){ 
        return edition; 
    }
    public void setEdition(int edition){ 
        this.edition = edition; 
    }

    public String getGender(){ 
        return gender; 
    }
    public void setGender(String gender){ 
        this.gender = gender; 
    }

    public int getStock(){ 
        return stock;
     }
    public void setStock(int stock){ 
        this.stock = stock; 
    }

    public double getPrice(){ 
        return price; 
    }

    public void setPrice(double price){ 
        this.price = price; 
    }

   @Override
       public String toString() {
           return "Livro [isbn=" + isbn + ", title=" + title + ", author=" + author +
                   ", edition=" + edition + ", gender=" + gender + ", stock=" + stock +
                   ", price=" + String.format("%.2f", price) + "]";
       }
}

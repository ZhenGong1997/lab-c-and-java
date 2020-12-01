/*
Name: Zhen Gong
Lab section: L03
Student number: 400079393
Lab4_UpperTriangularMatrix
*/


package matrix;


public class UpperTriangularMatrix {
        private int size;
        private int[] elements;
    
    public UpperTriangularMatrix(int n){
        if(n<=0){
            n=1;
        }
        this.size=n;
        this.elements=new int[n*(n+1)/2];
        for(int i=0;i<n*(n+1)/2;i++){
            this.elements[i]=0;
        }
    }
    
    //Matrix-> upperTri matrix(size,int effectivenum[])
    public UpperTriangularMatrix(Matrix upTriM) throws IllegalArgumentException{
        if(upTriM.isUpperTr()==false){
            throw new IllegalArgumentException("Not An Upper Triangular Matrix.");
        }
        else{
            this.size=upTriM.returnRow();
            this.elements=new int[size*(size+1)];
            int count=0;
   
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(upTriM.getElement(i, j)!=0){
                        this.elements[count]=upTriM.getElement(i, j);
                        count++;
                    }
                }
            }
        }    
    }
    
    public int getDim(){
        return this.size;
    }
    
    //Use method in Matrix class
    public int getElement(int i,int j) throws IndexOutOfBoundsException {
        if(i>=0&&i<=(size-1) && j>=0&&j<=(size-1)){
            Matrix a=new Matrix(size,size);
            int count=0;
            for(int x=0;x<size;x++){
                for(int y=0;y<size;y++){
                    if(y>=x){
                        a.setElement(elements[count], x, y);
                        count++;
                    }
                }
            }
            return a.getElement(i, j);
        }
        else{
            throw new IndexOutOfBoundsException("Invalid indexes");
        }
    }
    
    //Use setElement method in Matrix class and rewrite the matrix in terms of upperT matrix object
    public void setElement(int x,int i, int j)throws IndexOutOfBoundsException, IllegalArgumentException{
        Matrix a;
        a=fullMatrix();    
        
        int count=0;
            
            if(i>=0&&i<=(size-1) && j>=0&&j<=(size-1)){
                if(j<i&&x!=0){
                    throw new IllegalArgumentException("Incorrect Argument");
                }
                else if(j>=i){
                    a.setElement(x, i, j);
                    count=0;
                    for(int row=0;row<size;row++){
                        for(int col=0;col<size;col++){
                            if(col>=row){
                                this.elements[count]=a.getElement(row, col);
                                count++;
                            }
                        }
                    }
                }
                
            }
            else{
                throw new IndexOutOfBoundsException("Invalid indexes");
            }
            
    }
    
    public Matrix fullMatrix(){
        int count=0;
        Matrix a = new Matrix(size,size);
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(j>=i){
                    a.setElement(elements[count], i, j);
                    count++;
                }
            }
        }
        return a;
    }
    
    public void print1DArray(){
        for(int i=0;i<size*(size+1)/2;i++){
            System.out.print(elements[i]+"  ");
        }
    }
    //Write the matrix and output each element this matrix
    public String toString(){
        String output= new String();
        Matrix a=fullMatrix();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                output+=a.getElement(i, j)+"  ";
            }
            output+="\n";
        }
        return output;
    }
    
    
    public int getDet(){
        int det=1;
        Matrix a=fullMatrix();
        for(int i=0;i<size;i++){
            det*=a.getElement(i, i);
              
            
        }
        return det;
    }
    
    //Find the pattern in numerator, using double for loop to achieve this method
    public double[] effSolve(double[] b)throws IllegalArgumentException{
        
        Matrix mat=fullMatrix();
        UpperTriangularMatrix a=new UpperTriangularMatrix(mat);
        double[] x= new double[a.size];
       
        if(a.getDet()==0){
            throw new IllegalArgumentException("The determinant of A is zero.");
        }
        else if(b.length!=a.size){
            throw new IllegalArgumentException("Invalid dimension of b.");
        }
        
        else{
            for(int i=size-1;i>-1;i--){
                double numerator=b[i];
                for(int j=size-1;j>i;j--){
                    numerator-=mat.getElement(i, j)*x[j];
                }
                x[i]=numerator/mat.getElement(i, i);
                
            }
        }
        return x;
    }
}
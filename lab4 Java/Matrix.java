/*
Name: Zhen Gong
Lab section: L03
Student number: 400079393
Lab4_Matrix
*/



package matrix;


public class Matrix {
    private int rowsNum;
    private int colsNum;
    private int[][] matrixData;
    
    public Matrix(int row, int col){
        
        if(row<=0){
            this.rowsNum=3;
        }
        else{
            this.rowsNum=row;
        }
        if(col<=0){
            this.colsNum=3;
        }
        else{
            this.colsNum=col;
        }
       
       matrixData=new int[this.rowsNum][this.colsNum];
        for(int i=0;i<matrixData.length;i++){
            for(int j=0;j<matrixData[i].length;j++){
                this.matrixData[i][j]=0;
            }
        }
    }
    
    public Matrix(int[][]table){
        this.rowsNum=table.length;
        this.colsNum=table[0].length;
        this.matrixData=new int[rowsNum][colsNum];
        
        for(int i=0; i<this.rowsNum;i++){
            for(int j=0;j<this.colsNum;j++){
               this. matrixData[i][j]=table[i][j];
            }
        }
    }
    
    public int getElement(int i,int j) throws IndexOutOfBoundsException{
        if(i>=0&&i<=(this.rowsNum-1) && j>=0 && j<=(this.rowsNum-1)){
            return this.matrixData[i][j];
        }
        else{
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }
    
    public boolean setElement(int x,int i,int j){
        if(i>=0 && i<=(rowsNum-1) && j>=0 && j<=(rowsNum-1)){
            this.matrixData[i][j]=x;
            return true;
        }
        else{
            return false;
        }
    }
    
    public Matrix copy(){
        Matrix copyMatrix=new Matrix(this.matrixData);
        return copyMatrix;
    }
    
    public void addTo(Matrix m) throws ArithmeticException{
        if(m.rowsNum==this.rowsNum && m.colsNum==this.colsNum){
            for(int i=0;i<this.rowsNum;i++){
                for(int j=0;j<this.colsNum;j++){
                    this.matrixData[i][j]+=m.matrixData[i][j];
                }
            }
        }
        else{
            throw new ArithmeticException("Invalid operation");
        }
    }
    
    public Matrix subMatrix(int i, int j) throws ArithmeticException{
        if(i>=0 && i<=(this.rowsNum-1) && j>=0 && j<=(this.colsNum-1)){
            Matrix newMatrix=new Matrix((i+1),(j+1));
            for(int x=0;x<=i;x++){
                for(int y=0;y<=j;y++){
                    newMatrix.matrixData[x][y]=this.matrixData[x][y];
                }
            }
            return newMatrix;
        }
        else{
            throw new ArithmeticException("Submatrix not defined.");
        }
    }
    
    public boolean isUpperTr(){
        for(int i=0;i<this.rowsNum;i++){
            for(int j=0;j<this.colsNum;j++){
                if(j<i && this.matrixData[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
    }

    public static Matrix sum(Matrix[] matArray) throws ArithmeticException{
        boolean sameDimension=true;
        Matrix sumMatrix=new Matrix(matArray[0].rowsNum,matArray[0].colsNum);
            for (Matrix matArray1 : matArray) {
                if (matArray[0].rowsNum != matArray1.rowsNum || matArray[0].colsNum != matArray1.colsNum) {
                    sameDimension=false;
                    break;
                }
            }
        if(sameDimension==false){
            throw new ArithmeticException("The sum is not defined");
        }
        else{
            for(Matrix matArray1 : matArray){
                sumMatrix.addTo(matArray1);
            }
            return sumMatrix;
        }
    }  
    
    
    public String toString(){
       String output = new String(); // creates an empty string
        	for(int i = 0; i < this.rowsNum; i++){
        		for(int j = 0; j < this.colsNum; j++){
        			output += matrixData[i][j] + "  ";
        		}
        	output += "\n";
        	}
       		 return output; 
    }
    
    //Get the rowsNum of this matrix
    public int returnRow(){
        return this.rowsNum;
    }
    
}
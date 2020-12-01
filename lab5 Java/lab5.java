
package Lab5;

class SLLNode {
    int value;
    SLLNode next;
    public SLLNode(int i, SLLNode n){
        value = i;
        next=n;
    }
}







public class SLLSet {
    private int size;
    private SLLNode head;
    
    
    public SLLSet(){
        head=null;
        this.size=0;
    }
    
    public SLLSet( int[]sortedArray ){
        this.size=0;
        
        if(this.head==null){
            this.head=new SLLNode(sortedArray[0],null);
            this.size++;
        }
        
        SLLNode p=this.head;
        
        for(int i=1;i<sortedArray.length;i++){
            p.next=new SLLNode(sortedArray[i],null);
            p=p.next;
            this.size++;
        }
     }   
    
    
    public int getSize(){
        return this.size;
    }
    
    public SLLSet copy(){
        if(this.size==0){
            return new SLLSet();
        }
        SLLSet a=new SLLSet();
        a.size=this.size;
        //Copy the head
        SLLNode p=this.head;
        a.head=new SLLNode(p.value,null);
        p=p.next;
        //Copy the rest
        //SLLNode copy has to be initialized after a.head is assigned a node
        //Otherwise copy will be a null pointer.
        SLLNode copy=a.head;  
        
        while(p!=null){
            copy.next=new SLLNode(p.value,null);
            p=p.next;
            copy=copy.next;
        }
      
        return a;    
    }
    
    public boolean isIn(int v){
        for(SLLNode p=head;p!=null;p=p.next){
            if(p.value==v){
                return true;
            }
        }
        return false;
    }
    
    public void add(int v){
        
        if(isIn(v)==false){
            if(head==null){
                head=new SLLNode(v,null);
                this.size++;
            }
            else{
                if(v<head.value){
                    head=new SLLNode(v,head);
                    this.size++;
                }
                else{
                    SLLNode p=head;
                    while(p.next!=null){
                        if(v<p.next.value){
                            p.next=new SLLNode(v,p.next);
                            this.size++;
                            return;
                        }
                        p=p.next;
                    }
                    p.next=new SLLNode(v,null);
                    size++;
                }
               
            }
        
        }
    }
    
    public void remove(int v){
        SLLNode p;
        if(head==null){
            
        }
        else if(head.value==v){
                head=head.next;
                this.size--;
        }
        else{
            for(p=head;p.next!=null;p=p.next){
                if(p.next.value==v){
                    p.next=p.next.next;
                    this.size--;
                    break;
                }
            }
        }

    }

        
    public SLLSet union(SLLSet s){
        SLLNode a=this.head;
        
        SLLSet c=s.copy();
        SLLNode b=c.head;
        if(this.head==null){
            return c;
        }
        else if(c.head==null){
            return this;
        }
        else{
            while(a.value<c.head.value){
                c.head=new SLLNode(a.value,head);
                a=a.next;
            }
            if(a.value==c.head.value){
                a=a.next;
            }
            while(a!=null){
                while(b.next!=null){
                    if(a==null){
                        break;
                    }
                    if(a.value==b.next.value){
                    a=a.next;
                    b=b.next;
                    }
                    else if(a.value>b.next.value){
                        b=b.next;
                    }
                    else{
                        b.next=new SLLNode(a.value,b.next);
                        c.size++;
                        a=a.next;
                        b=b.next;
                    }
                    
                }
                if(a==null){
                    break;
                }
                else{
                    b.next=new SLLNode(a.value,null);
                    s.size++;
                }
            }
        }
        
        
       
        return c;
        
    }
    
    public SLLSet intersection(SLLSet s){
        
        SLLSet inter=new SLLSet();
        SLLNode a=this.head;
        SLLNode b=s.head;
        
        if(this.head==null||s.head==null){
            return new SLLSet();
        }
        
        
        while(inter.head==null&&a!=null&&b!=null){
            if(a.value<b.value){
                a=a.next;
            }
            else if(a.value>b.value){
                b=b.next;
            }
            else{
                inter.head=new SLLNode(a.value,null);
                inter.size++;
                a=a.next;
                b=b.next;
                break;
            }
        }
        if(inter.head==null){
            return new SLLSet();
        }
        SLLNode m=inter.head;
        /*
        System.out.println("head is "+m.value);
        System.out.println(a.value+"*****"+b.value+"here!!!!!!!!");*/
        while(a!=null&&b!=null){
            if(a.value<b.value){
                    a=a.next;
            }
            else if(a.value>b.value){
                    b=b.next;
            }
            else if(a.value==b.value){
                    m.next=new SLLNode(b.value,null);
                    
                    inter.size++;
                    a=a.next;
                    b=b.next;
            }
        }
        
        return inter;  
        
        
    }
    
    public SLLSet difference(SLLSet s){
        SLLSet diff=new SLLSet();
        SLLNode p=this.head;
        SLLNode m=s.head;
        
        while(p!=null){
            if(s.isIn(p.value)==false){
                diff.add(p.value);
            }
            p=p.next;
        }
        
        return diff;
    }
    
    
    public static SLLSet union(SLLSet[] sArray){
        SLLSet unionArray=new SLLSet();
        for (SLLSet sArray1 : sArray) {
            SLLNode p = sArray1.head;
            while(p!=null){
                unionArray.add(p.value);
                p=p.next;
            }
        }
        return unionArray;
    }
            
            
    @Override
    public String toString(){
        SLLNode p;
        String a="";
        
        if(head==null){
            return "It is an empty set!!";
        }
        for(p=head;p.next!=null;p=p.next){
            a+=p.value+",";
        }
        a+=p.value;
        
        return a;
    }
    
   

}
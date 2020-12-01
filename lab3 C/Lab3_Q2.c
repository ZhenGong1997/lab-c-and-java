/*
Name: Zhen Gong
Lab section: L03
Student number: 400079393
Lab3_Q2
*/

#include <stdio.h>
#include <stdlib.h>


typedef struct
{
    int id;
    char firstname[15];
    char lastname[15];
    int grade1;
    int grade2;
    float finalgrade;
}student;

void sort(student**list,int size);
student** create_class_list(char *filename,int *sizePtr);
int find(int idNo,student**list, int size);
void input_grades(char *filename,student **list, int size);
void compute_final_course_grades(student** list,int size);
void output_final_course_grades(char*filename,student**list,int size);
void print_list(student**list,int size);
void withdraw(int idNo, student** list, int* sizePtr);
void destroy_list(student **list, int *sizePtr);


int main()
{
    //Declare variables
    int size=0;
    student**list=NULL;

    //Set up all the information that are necessary and prints out the list
    list=create_class_list("test.txt",&size);
    input_grades("input.txt",list,size);
    compute_final_course_grades(list,size);
    print_list(list,size);

    //Output final grades to a text file
    output_final_course_grades("finalgrades.txt",list,size);

    //withdraw a student from the list
    withdraw(222222222,list,&size);
    printf("\nNewlist: \n");
    print_list(list,size);

    //destroy the list
    destroy_list(list,&size);
    printf("\nDestroyed list: \n");


    return 0;
}

void sort(student**list,int size)
{
    for (int i=0;i<size;i++){
        for(int j=0;j<size-1;j++){
            if(list[j]->id > list[j+1]->id){

                student* temp=NULL;
                temp=list[j];
                list[j]=list[j+1];
                list[j+1]=temp;
            }
        }
    }
}

student** create_class_list(char *filename,int *sizePtr)
{
    int id;
    char firstname[15];
    char lastname[15];


    FILE*infile=fopen(filename,"r");

    //store the number of students in sizePtr
    fscanf(infile,"%d",sizePtr);

    //create a student structure contains an array of student
    student** namelist=malloc(sizeof(*sizePtr));
    for(int i=0;i<*sizePtr;i++){
        namelist[i]=malloc(sizeof(student));
    }



    int studentcount=0;
    while(!feof(infile)){
        int lenfirst=0;
        int lenlast=0;

        fscanf(infile,"%d",&id);
        fscanf(infile,"%s",firstname);
        fscanf(infile,"%s",lastname);
        namelist[studentcount]->id=id;

        for(int i=0;firstname[i]!='\0';i++){
            namelist[studentcount]->firstname[i]=firstname[i];
            lenfirst++;
        }
        namelist[studentcount]->firstname[lenfirst]='\0';

        for(int i=0;lastname[i]!='\0';i++){
            namelist[studentcount]->lastname[i]=lastname[i];
            lenlast++;
        }
        namelist[studentcount]->lastname[lenlast]='\0';

        namelist[studentcount]->grade1=0;
        namelist[studentcount]->grade2=0;
        namelist[studentcount]->finalgrade=0.0;
        studentcount++;

    }
    //Sort list
    sort(namelist,*sizePtr);

    fclose(infile);

    return namelist;
}

//find whether a input student nunmber is in the array of student lists
int find(int idNo,student**list, int size)
{
    for(int i=0;i<size;i++){
        if(list[i]->id==idNo){
            return i;
        }
    }
    return -1;
}

//Read a file and input grades into the student array of list
void input_grades(char *filename,student **list, int size)
{
    int position;
    int id;
    int grade1;
    int grade2;
    FILE*infile=fopen(filename,"r");

    while(!feof(infile)){
        fscanf(infile,"%d",&id);
        position=find(id,list,size);
        fscanf(infile,"%d",&grade1);
        fscanf(infile,"%d",&grade2);
        list[position]->grade1=grade1;
        list[position]->grade2=grade2;
    }
    fclose(infile);
}


void compute_final_course_grades(student** list,int size)
{
    for(int i=0;i<size;i++){
        list[i]->finalgrade = ((list[i]->grade1) + (list[i]->grade2))/2.0;
    }
}


void output_final_course_grades(char*filename,student**list,int size)
{
    FILE*outfile=fopen(filename,"w");
    fprintf(outfile,"%d\n",size);

    for(int i=0;i<size;i++){
        fprintf(outfile,"%d %f\n",list[i]->id,list[i]->finalgrade);
    }
    fclose(outfile);
}

void print_list(student**list,int size)
{
    for(int i=0;i<size;i++){
        printf("ID:%d, name: %s %s, project 1 grade: %d,",list[i]->id,list[i]->firstname,list[i]->lastname,list[i]->grade1);
        printf(" project 2 grade: %d, final grade: %f.\n",list[i]->grade2,list[i]->finalgrade);
    }
}


void withdraw(int idNo, student** list, int* sizePtr)
{
    if(find(idNo,list,*sizePtr)==-1){
        printf("ID is not found.");
    }
    else{
        for(int i=find(idNo,list,*sizePtr);i<*sizePtr;i++){
            list[i]=list[i+1];
        }
        free(list[*sizePtr]);
        *sizePtr-=1;
    }
}

void destroy_list(student **list, int *sizePtr)
{
    for(int i=0;i<*sizePtr;i++){
        free(list[i]);
    }

    *sizePtr=0;
}
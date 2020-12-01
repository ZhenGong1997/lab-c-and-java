/*
Name: Zhen Gong
Lab section: L03
Student number: 400079393
Lab3_Q1
*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
char *my_strcat(const char *str1, const char *str2);
int main()
{
    printf("%s\n",my_strcat("Hello","world!"));
    return 0;
}


char *my_strcat(const char *str1, const char *str2)
{
    int len1=strlen(str1);
    int len2=strlen(str2);

    char *ptr=0;
    ptr=malloc((len1+len2+1)*sizeof(char));


    for(int i=0;str1[i]!='\0';i++){
        *(ptr+i)=str1[i];
    }

    for(int j=0;str2[j]!='\0';j++){
        *(ptr+j+len1)=str2[j];
    }
    ptr[len1+len2]='\0';
    return ptr;


}
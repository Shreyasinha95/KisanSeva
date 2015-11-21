#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<sys/types.h>
void GETLINE();
void PROCESSLINE();
void DEFINE();
void EXPAND();


 FILE *expanded;	//pointer to output file
 FILE *input;		//pointer to input file

 char label[10],opcode[10],operand[25];
 char line[20];	//current line
 int namcount=0;	//total no. of names in NAMTAB
 int defcount=0;	//total no. of lines in DEFTAB
 int EXPANDING;
 int curr;



 struct namtab
 {
 	char name[10];
 	int start,end;
 }mynamtab[15];
     
 struct deftab
 {
        char macroline[25];
  }mydeftab[15];

 struct argtab
 {
       char arg[3][9];
 }myargtab;

//Main Macro Processor

void main()
{
	char option;
	EXPANDING=0;
	
	input =fopen("ip.txt","r");      //opens the input file   in readable mode                 
	expanded=fopen("expanded.txt","w");	//opens the output file in writable mode
	GETLINE();	//gets the current line in the form of label,opcode and operand
 
	while(strcmp(opcode,"END")!=0)	//string not equal to END
	{  
		PROCESSLINE();		
       		GETLINE();
	}
        fprintf(expanded,"%s",line);	//copies the 'line' in expandable file
     	printf("\n*******************************\n\nEXPANDED FILE GENERATED!\n\n*******************************\n");
/*
	printf("\nDo you want to open the expanded file?(Y/N)\n");
	scanf("%c",&option);
     	if(option=='Y'||option=='y')
		system("sh sscript.sh");
	else
		exit(0);*/
  }
                            


//GETLINE Function

void GETLINE()
{    
	char word1[10],word2[10],word3[10],buff[10];
	int count=0,i,j=0;
	if(EXPANDING)
		strcpy(line,mydeftab[curr++].macroline);	//copy the line from deftab to 'line' and increment the value of curr
 	else fgets(line,20,input);				//gets till 20 characters from input file into 'line'

        opcode[0]='\0';	//putting null character in the arrays
	label[0]='\0';
	operand[0]='\0';
	word1[0]='\0';
	word2[0]='\0';
	word3[0]='\0';
        
        for(i=0;line[i]!='\0';i++)	//continue for till line ends
        {
        	if(line[i]!=' ')	
                	buff[j++]=line[i];	//gets words in the buffer
                else
                {
                	buff[j]='\0';		//end the buffer
                 	strcpy(word3,word2);	//shifting of words
                    	strcpy(word2,word1);
                       	strcpy(word1,buff);
                     	j=0;
			count++;
                }//end ifelse
        }//end for

        buff[j-1]='\0';
        strcpy(word3,word2);
        strcpy(word2,word1);
        strcpy(word1,buff);
                   
	switch(count)
        {
        	 case 0:strcpy(opcode,word1);
			break;
                 case 1:{strcpy(opcode,word2);strcpy(operand,word1);}
			break;
                 case 2:{strcpy(label,word3);strcpy(opcode,word2);strcpy(operand,word1);} //sorting the words in the line
			break;
        }//end switch
}//end getline
                   


//PROCESSLINE Function
void PROCESSLINE()
{
	int i;
        for(i=0;i<namcount;i++) //till the total no. of macros
        	if(!strcmp(opcode,mynamtab[i].name))	//if opcode matches in the NAMTAB
                {
                	EXPAND();return;		//then expand
                }
                { 
                        if(!strcmp(opcode,"MACRO"))	//if "MACRO" keyword is encountered
		                DEFINE();		//then define
                        else fprintf(expanded,"%s",line);	//otherwise copy it into the expanded file
                }
}//end processline
   


//DEFINE Function   
void DEFINE()
{
	int LEVEL,i=0,j=0,k=0;
        char param[3][9];
        char s[3];
        strcpy(s,"123");
 
        strcpy(mynamtab[namcount].name,label);		//enter Macro name in NAMTAB
        mynamtab[namcount].start=defcount;		//enter starting pointer 
        strcpy(mydeftab[defcount].macroline,line);	//enter the line in the deftab which the starting pointer is pointing
      
        while(operand[i]!='\0')	//while operand is there
        {
        	if(operand[i]!=',')
                	param[j][k++]=operand[i];
                else
                {
                	param[j++][k]='\0';
                        k=0;
                }
                i++;
        }//end while
        param[j][k]='\0';
                             
        LEVEL=1;
        //for nested macro
        while(LEVEL>0)
        	{
                	GETLINE();
           		if(operand[0]!='\0')	//if operand is there
             		{
            			for(i=0;i<3;i++)
			        {
			        	if(!strcmp(operand,param[i]))
				        {
						operand[0]='?';		//substituting the positional parameter
            					operand[1]=s[i];
            					operand[2]='\0';
             				}//end if
             			}//end for
             		}//end if
           		if(!strcmp(opcode,"MACRO"))
            			LEVEL++;
           		else if(!strcmp(opcode,"MEND"))
            			LEVEL--;

            		strcpy(mydeftab[defcount].macroline,opcode);	//copy the opcode in deftab
            		if(operand[0]!='\0')
	            	{
		            strcat(mydeftab[defcount].macroline," ");	//copy the operand in deftab
        		    strcat(mydeftab[defcount].macroline,operand);
        		    strcat(mydeftab[defcount].macroline,"\n");
        	        }
        	    	strcat(mydeftab[defcount++].macroline,"\n");
		}
                mynamtab[namcount++].end=defcount;	//get the end pointer to deftab
}//end define
                      
         

//EXPAND Function

void EXPAND()
{
	int i,end=0,j=0,k=0;
        EXPANDING=1;
        int arg=0;
                      
        fprintf(expanded,"//%s",line);		//comments the call
        for(i=0;i<namcount;i++)			
        {
        	if(!strcmp(opcode,mynamtab[i].name))	//if opcode matches name available in the NAMTAB
                {
                	curr=mynamtab[i].start;
                        end=mynamtab[i].end;
                        while(operand[i]!='\0')		//if operand is there
                        {
                        	if(operand[i]!=',')
                                myargtab.arg[j][k++]=operand[i];
                                else
                                {
                                        myargtab.arg[j++][k]='\n';
                                	k=0;
                                }
                              
                                i++;
                        }//endwhile                             
                        myargtab.arg[j][k]='\n';	//generating the ARGTAB
                 }//end if
        }//end for
        while(curr<(end-1)) //continue till end of the DEFTAB is reached
        {
        	GETLINE();
                if(operand[0]=='?')	//if positional parameter
		        strcpy(operand,myargtab.arg[operand[1]-'0'-1]);	//then put its value from ARGTAB in the operand
			fprintf(expanded,"%s %s %s",label,opcode,operand); //copy the whole line in expanded file
        }
        EXPANDING=0; //end of expansion
}//end expand


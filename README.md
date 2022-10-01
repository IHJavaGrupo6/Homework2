# LBL Trucking company CRM

Use Case diagram:  
![](https://raw.githubusercontent.com/IHJavaGrupo6/Homework2/main/CRM%20Use%20Case%20diagram.png?token=GHSAT0AAAAAABYS5733VR63OUCXJGCEZMNGYZXB2NA)
Class diagram:  
![](https://raw.githubusercontent.com/IHJavaGrupo6/Homework2/main/CRM%20Class%20diagram.png?token=GHSAT0AAAAAABYS57322KNIWCO6Q2JFEJICYZXB2IQ)


## --Instructions--

==LBL Trucking company CRM==  
Menu:
~~~
=========  
MAIN MENU  
Available commands:  
• New lead  
• Show leads  to show the list of existing leads  
Existing leads: 0  
• Look up lead + id  to find a lead by its id number and display its info  
• Convert lead + id  to find a lead by its id number and convert it into a new opportunity  
• Show opportunities  to show the list of existing opportunities (both open and closed)  
Existing opportunities: 0  
• Look up opportunity + id  to find a lead by its id number and display its info  
• Close-Won + id  to close an oportunity that ended with a sale  
• Close-Lost + id  to close a lost oportunity  
• Exit  
What do you want to do?  
=========
~~~~

### Option -New lead-
Insert the comamand "New Lead" to generate a new lead, introducing by inputs the lead information(Name, Phone Number,
email and Company Name).

~~~~
=========  
What do you want to do?  
new lead  
Creating a new lead:  
Please enter the name of the new lead:  
Quim  
Please enter a phone number for the new lead:  
666999555  
Please enter an email for the new lead:  
quim@myenterprise.com  
Please enter the name of the company for the new lead:  
My Enterprise  
New lead created:  
Lead: id = 0, name = Quim, phoneNumber = 666999555, email = quim@myenterprise.com, companyName = My Enterprise  
=========
~~~~
### Option -Show leads-
Introduce the command "Show leads" to display the list of leads(HashMap object).
~~~~
=========  
What do you want to do?  
show leads  
Existing leads:

•Lead: id = 0, name = Quim, phoneNumber = 666999555, email = quim@myenterprise.com, companyName = My Enterprise

•Lead: id = 1, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2

=========
~~~~

### Option -Look up lead + id-
Insert the command "look up lead id" to show the lead that corresponds to the entered numeric id.
~~~~
=========  
What do you want to do?  
look up lead 1  
Lead: id = 1, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2  
=========
~~~~

### Option -Convert lead + id-
Insert the command "Convert id" to turn the lead into a contact, opportunity and an Account. The command activates
a chain the command in which it will be shown that the contact has been generated, it will ask you to enter the
following data (Product type, number of trucks) to create the opportunity and finally an account will be created with
the data (Industry type, nº employees, city and country) that the user will be asked for.
~~~~
=========  
What do you want to do?  
convert 1  
Lead converted into a new contact:  
Contact: id = 0, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2

Creating a new opportunity:  
Please enter product type: HYBRID, FLATBED or BOX  
hybrid  
Please enter the number of trucks being considered for purchase:  
50  
Created a new opportunity:  
Opportunity: id = 0, product = HYBRID, trucks quantity = 50, status = OPEN  
Decision maker Contact: id = 0, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2

Creating a new account:  
Please enter industry type: PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER  
produce  
Please enter the number of employees in the company:  
1200  
PLease enter the city in which the company is based:  
Barcelona  
PLease enter the country in which the company is based:  
España  
Created a new account:  
Account: id = 0, industry=PRODUCE, employeeCount=1200, city='Barcelona, country='España  
Contact List  
[Contact: id = 0, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2
]  
Opportunity List  
[Opportunity: id = 0, product = HYBRID, trucks quantity = 50, status = OPEN
Decision maker Contact: id = 0, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2
]  
=========
~~~~
### Option -Show opportunities-
Insert the command "show opportunities" to display a list of all opportunities.
~~~~
=========  
What do you want to do?  
show opportunities  
Existing opportunities:  

•Opportunity: id = 0, product = HYBRID, trucks quantity = 50, status = OPEN  
Decision maker Contact: id = 0, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2  
=========
~~~~
### Option -Look up opportunity + id-
Introduce the command "look up opportunity id" to show an opportunity by its id number.
~~~~
=========  
What do you want to do?  
look up opportunity 0  
Opportunity: id = 0, product = HYBRID, trucks quantity = 50, status = OPEN  
Decision maker Contact: id = 0, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2  
=========
~~~~
### Option -Close-Won + id-
Introduce de command "close won id" to close an opportunity that ended with a sale.
~~~~
=========  
What do you want to do?  
close won 0  
Opportunity: id = 0, product = HYBRID, trucks quantity = 30, status = CLOSED_WON  
Decision maker Contact: id = 0, name = Quim, phoneNumber = 666222111, email = quim@myenterpryse.com, companyName = My Enterpryse  
=========
~~~~

### Option -Close-Lost + id-
Insert de command "close lost id" to close a lost opportunity by its id number.
~~~~
=========  
What do you want to do?  
close lost 0  
Opportunity: id = 0, product = HYBRID, trucks quantity = 30, status = CLOSED_LOST  
Decision maker Contact: id = 0, name = Quim, phoneNumber = 666444888, email = quim@myenterpryse.com, companyName = My enterpryse  
=========
~~~~
### Option -Exit-
Insert the command "exit" to turn off the CRM.
~~~~
=========
What do you want to do?  
exit  
Good bye!  
=========
~~~~

## Testing


## Credits

### Renegados Staff

Aña Popova  
Irina Tataru  
Oscar Curto  
Danny Mejía  
Joaquim Crous  


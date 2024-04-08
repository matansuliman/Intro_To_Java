# Intro to JAVA

## Date class:
### instance variabels:
* int int_day - between 1-31
* int int_month - between 1-12
* int int_year - whole positive four-letter number.
### constructors:
* public Date(int day, int month, int year)
* public Date(Date other)
### get/sets:
* public int getDay() , public void setDay(int dayToSet)
* public int getMonth() , public void setDay(int monthToSet)
* public int getYear() , public void setDay(int yearToSet)
* if we get an Illegal value we will not set it.
### other functions:
* public boolean equals(Date other)
* public boolean before(Date other)
* public boolean after(Date other)
* public int difference(Date other)
* public string toString()
* public Date tomorrow()

## Car class:
### instance variabels:
* int _id - plate license number, 7 digits, whole and positive.
* char _type - rating of the car
* string _brand - 
* boolean _isManual - true/false.
### constructors:
* public Car(int id, int type, string brand, boolean isManual)
* public Car(Car other)
* if given an Illegal ID then the Default is 9999999.
* rating is a char A,B,C,D. The default is A.
* brand is a nonempty string.
### get/sets:
* public int getId() , public void setID(int id)
* public char getType() , public void setType(int type)
* public string getBrand() , public void setBrand(string brand)
* public boolean isManual() , public void setIsManual(boolean isManual)
* if we get an Illegal value we will not set it.
### other functions:
* public boolean equals(Car other)
* public boolean better(Car other)
* public boolean worse(Car other)
* public string toString()


## Rent class:
represent a car rent.
### instance variabels:
* string _name - customer name
* Car _car - the rental car
* Date _pickDate - rental start date
* Date _returnDate - rental retrun date
### constructors:
* public Rent(string _name, Car _car, Date _pickDate, Date _returnDate)
* public Rent(Rent other)
* The pickDate has to be at least one day before the returnDate.
* The default is that the return date is one day after the pick date.
### get/sets:
* public string getName() , public void setName(string name)
* public Car getCar() , public void setCar(Car car)
* public Date getPickDate() , public void setpickDate(Date pickDate)
* public Date getReturnDate() , public void getReturnDate(Date returnDate)
* if we get an Illegal value we will not set it.
### other functions:
* public boolean equals(Rent other)
* public int howManyDays()
* public int getPrice()
* public int upgrade(Car newCar)
* public Rent overlap(Rent other)
* public string toString()


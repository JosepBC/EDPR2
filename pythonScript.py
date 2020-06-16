import pandas as pd

data = pd.read_csv("airports/random/randomAirports1.csv")

for i in range (2,100):
	data.add(pd.read_csv("airports/random/randomAirports"+str(i)+".csv"))

data.div(100)

data.to_csv("airports/randomAirports.csv", index=False)
#--------------
data = pd.read_csv("electrica/random/randomElectrica1.csv")

for i in range (2,100):
	data.add(pd.read_csv("electrica/random/randomElectrica"+str(i)+".csv"))

data.div(100)

data.to_csv("electrica/randomElectrica.csv", index=False)
#---------------
data = pd.read_csv("email/random/randomEmail1.csv")

for i in range (2,100):
	data.add(pd.read_csv("email/random/randomEmail"+str(i)+".csv"))

data.div(100)

data.to_csv("email/randomEmail.csv", index=False)
#---------------
data = pd.read_csv("wtw/random/randomWtw1.csv")

for i in range (2,100):
	data.add(pd.read_csv("wtw/random/randomWtw"+str(i)+".csv"))

data.div(100)

data.to_csv("wtw/randomWtw.csv", index=False)

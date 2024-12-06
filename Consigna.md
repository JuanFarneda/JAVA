Un banco maneja muchas cuentas bancarias. Cada cuenta está asociada a una sola 
persona. Las cuentas pueden ser en dólares o en pesos. La distinción es importante, ya que 
si bien las dos cuentas tienen una persona como titular, un saldo y un CBU asociado, hacen 
un diferente manejo de las operaciones depositar y retirar fondos. Las personas poseen 
nombre, apellido, número de documento y una dirección en donde viven. Al banco le 
interesa poder consultar por las provincias y ciudades donde viven las personas. 
Tareas a realizar: 
1. Escriba el diagrama de clases para los objetos que considere necesarios. 
2. Implemente en Java la siguiente secuencia:  
● creación de un banco 
● creación de 3 cuentas bancarias en pesos 
● creación 2 cuentas bancarias en dólares 
● deposite 1000 pesos en todas las cuentas en pesos 
● deposite 100 dólares en todas las cuentas en dólares 
● extraiga 400 pesos de una cuenta en pesos 
● extraiga 50 dólares de una cuenta en dólares 
● deposite 20 dólares en una cuenta en dólares 
● intente extraer 5000 pesos de una cuenta en pesos 
● transfiera dinero de una cuenta en dólares a una cuenta en pesos 
● transfiera dinero de una cuenta en pesos a una cuenta en dólares 
● obtener todas las cuentas de personas de una provincia 
● obtener todas las cuentas de personas de una ciudad 
Consideraciones:  
● Las personas deben ser creadas con todos sus datos en el constructor. 
● Al ser creadas todas las cuentas tienen un saldo 0. 
● El número de CBU se genera automáticamente para cada cuenta. 
● Ninguna de las cuentas puede operar al descubierto (extraer más dinero del que 
tiene). 
● El banco permite hacer transferencias entre dos cuentas sin importar el tipo de 
cuenta, el mismo banco realiza la conversión entre las divisas.

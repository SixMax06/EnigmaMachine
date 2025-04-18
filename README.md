# Simulatore Macchina Enigma

## Breve storia della Macchina
La macchina Enigma è un dispositivo elettromeccanico capace di cifrare e decifrare i messaggi.
Nasce da un tentativo di commercializzazione che però fallisce ed è stata ampliamente utilizzata dalle forze armate naziste durante la seconda guerra mondiale.

Nonostante le svariate modifiche nel tempo, alcuni matematici polacchi riuscirono a decifrare i messaggi della macchina, considerata fino a quel momento indecifrabile.
Questo aprì le porte a numerosi matematici in tutto il mondo. Fu molto celebre il lavoro di Alan Turing, che riuscì a decifrare l'ultima e più complessa versione della macchina verso la fine della seconda guerra mondiale.

## Introduzione
Il simulatore ha lo scopo di avvicinarsi il più possibile alla macchina originale. Infatti è munito di una tastiera "fisica", un pannello di lampade ed un pannello con i rotori. Si può interagire con la macchina virtuale solamente dalla tastiera integrata e dai bottoni impostati nel programma.

Il simulatore si presenta in questo modo:

![Screenshot 2025-04-14 131712](https://github.com/user-attachments/assets/ee11bdee-a55f-405a-a449-5860dad7d31c)

## Funzionamento
Il programma utilizza varie classi Java per gestire la criptazione dei caratteri.

Internamente ogni rotore della macchina è rappresentato da una lista di caratteri che rappresenta l'alfabeto cifrato del rotore. Alla pressione di un carattere, i rotori ruotano autonomamente per cambiare la cifratura nell'esatto modo in cui una vera macchina enigma funziona.

Inoltre il simulatore offre la possibilità di ruotare manualmente i rotori e di cambiare il tipo di alfabeto cifrato che utilizzano, espandendo di gran lunga le possibili combinazioni del messaggio.

## Utilizzo
Utilizzando la tastiera del simulatore possiamo vedere che alla pressione di ogni carattere una lampada del pannello superiore si accende, che rappresenta il carattere cifrato. Nel caso seguente alla pressione del carattere 'A' si illumina la lampada contenente il carattere 'U':

![Screenshot 2025-04-14 131828](https://github.com/user-attachments/assets/77f84f2f-c374-452f-92f7-3d59ce4a244a)

Nel primo pannello sono rapresentati i tre rotori della macchina, ognuno con un carattere che rappresenta la rotazione. Ogni rotore è munito di due bottoni utilizzati per ruotare manualmente i rotori. Dopo aver ruotato casualmente i rotori, possiamo vedere che alla pressione del carattere 'A' si illumina il carattere 'E', al posto del precedente carattere 'U'

![Screenshot 2025-04-14 131925](https://github.com/user-attachments/assets/87a04b68-ef5b-4964-bd9d-482d188fa510)

Inoltre sotto ad ogni rotore si trova un menù a tendina che permette all'utente di cambiare il tipo dei rotori, muniti di alfabeti cifrati diversi:

![Screenshot 2025-04-14 132022](https://github.com/user-attachments/assets/1e41eda8-4b90-4c90-a791-39221d0d164a)

---

### Qui segue un breve video dimostrativo del simulatore in funzione:



https://github.com/user-attachments/assets/0ef1aa64-c6d9-4811-a0eb-c485d6b2bdca



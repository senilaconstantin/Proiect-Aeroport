.586
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem biblioteci, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern exit: proc
extern malloc: proc
extern memset: proc
extern printf: proc
includelib canvas.lib
extern BeginDrawing: proc


;declaram simbolul start ca public - de acolo incepe executia
public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data
;aici declaram date
window_title DB "Proiect Asamblare - TABLE",0
area_width EQU 1000
area_height EQU 600
area DD 0

format db "%d", 0

counter DD 0 ; numara evenimentele de tip timer

arg1 EQU 8
arg2 EQU 12
arg3 EQU 16
arg4 EQU 20

matr dd 0

symbol_width EQU 10
symbol_height EQU 20
piesa_width equ 40
piesa_height equ 40
include cele24butoane.asm
include afisare.asm
include imagine.asm
include digits.inc
include letters.inc
include piesa.inc

B1x equ 740
B1y equ 335
B2x equ 100
B2y equ 20
clickalb dd 1
clicknegru dd 1
click1 dd 0
ord dd 0
ver dd 0

piese_albe db 0, 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0
;piese_albe db 0, 0, 5, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 2, 0


piese_negre db 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, 0, 0

button_x equ 790
button_y equ 288
button_size equ 50
x dd 0
y dd 0
zar dd 6
zar1 dd 6
zar2 dd 6
zarr1 dd 0
zarr2 dd 0
dubla dd 0
suma_albe dd 0
suma_negre dd 0

.code
; procedura make_text afiseaza o litera sau o cifra la coordonatele date
; arg1 - simbolul de afisat (litera sau cifra)
; arg2 - pointer la vectorul de pixeli
; arg3 - pos_x
; arg4 - pos_y
make_text proc
	push ebp
	mov ebp, esp
	pusha
	
	mov eax, [ebp+arg1] ; citim simbolul de afisat
	cmp eax, 'A'
	jl make_digit
	cmp eax, 'Z'
	jg make_digit
	sub eax, 'A'
	lea esi, letters
	jmp draw_text
make_digit:
	; cmp eax, '0'
	; jl make_space
	; cmp eax, '9'
	; jg make_space
	; sub eax, '0'
	; lea esi, digits
	jmp draw_text
make_space:	
	mov eax, 26 ; de la 0 pana la 25 sunt litere, 26 e space
	lea esi, letters
	
draw_text:
	mov ebx, symbol_width
	mul ebx
	mov ebx, symbol_height
	mul ebx
	add esi, eax
	mov ecx, symbol_height
bucla_simbol_linii:
	mov edi, [ebp+arg2] ; pointer la matricea de pixeli
	mov eax, [ebp+arg4] ; pointer la coord y
	add eax, symbol_height
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+arg3] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, symbol_width
bucla_simbol_coloane:
	cmp byte ptr [esi], 0
	je simbol_pixel_alb
	mov dword ptr [edi], 0
	jmp simbol_pixel_next
simbol_pixel_alb:
	mov dword ptr [edi], 0FFFFFFh
simbol_pixel_next:
	inc esi
	add edi, 4
	loop bucla_simbol_coloane
	pop ecx
	loop bucla_simbol_linii
	popa
	mov esp, ebp
	pop ebp
	ret
make_text endp

make_piesa proc
	push ebp
	mov ebp, esp
	pusha
	
	mov eax, [ebp+arg1] ; citim simbolul de afisat
	cmp eax, 'a'
	jl afarap
	cmp eax, 'b'
	jg afarap
	sub eax, 'a'
	lea esi, piesa
	jmp draw_piesa
	
draw_piesa:
	mov ebx, piesa_width
	mul ebx
	mov ebx, piesa_height
	mul ebx
	add esi, eax
	mov ecx, piesa_height
bucla_piesa_linii:
	mov edi, [ebp+arg2] ; pointer la matricea de pixeli
	mov eax, [ebp+arg4] ; pointer la coord y
	add eax, piesa_height
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+arg3] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, piesa_width
bucla_piesa_coloane:
	cmp byte ptr [esi], 0
	je piesa_pixel_transparent
	
	cmp byte ptr [esi], 2
	je piesa_pixel_albu
	
	mov dword ptr [edi], 0
	jmp piesa_pixel_next
	
piesa_pixel_transparent:
	;mov dword ptr [edi], 0FFFFFFh
jmp piesa_pixel_next
piesa_pixel_albu:
	mov dword ptr [edi], 0FFFFFFh
piesa_pixel_next:
	inc esi
	add edi, 4
	loop bucla_piesa_coloane
	pop ecx
	loop bucla_piesa_linii
	
	
afarap:
	popa
	mov esp, ebp
	pop ebp
	ret
make_piesa endp


make_zar proc
	push ebp
	mov ebp, esp
	pusha
	
	mov eax, [ebp+arg1] ; citim simbolul de afisat
	cmp eax, '0'
	jl afarapp
	cmp eax, '6'
	jg afarapp
	sub eax, '0'
	lea esi, digits
	jmp draw_zar
	
draw_zar:
	mov ebx, 20
	mul ebx
	mov ebx, 20
	mul ebx
	add esi, eax
	mov ecx, 20
bucla_zar_linii:
	mov edi, [ebp+arg2] ; pointer la matricea de pixeli
	mov eax, [ebp+arg4] ; pointer la coord y
	add eax, 20
	sub eax, ecx
	mov ebx, area_width
	mul ebx
	add eax, [ebp+arg3] ; pointer la coord x
	shl eax, 2 ; inmultim cu 4, avem un DWORD per pixel
	add edi, eax
	push ecx
	mov ecx, 20
bucla_zar_coloane:
	cmp byte ptr [esi], 0
	je zar_pixel_alb
	
	mov dword ptr [edi], 0
	jmp zar_pixel_next
zar_pixel_alb:
	mov dword ptr [edi], 0FFFFFFh
jmp zar_pixel_next
zar_pixel_next:
	inc esi
	add edi, 4
	loop bucla_zar_coloane
	pop ecx
	loop bucla_zar_linii
	
	
afarapp:
	popa
	mov esp, ebp
	pop ebp
	ret
make_zar endp



; functia de desenare - se apeleaza la fiecare click
; sau la fiecare interval de 200ms in care nu s-a dat click
; arg1 - evt (0 - initializare, 1 - click, 2 - s-a scurs intervalul fara click)
; arg2 - x
; arg3 - y
draw proc
	push ebp
	mov ebp, esp
	pusha
	
	mov eax, [ebp+arg1]
	cmp eax, 1
	jz evt_click
	cmp eax, 2
	jz evt_timer ; nu s-a efectuat click pe nimic
	
	jmp afisare_litere
	
evt_click:	
	mov eax, [ebp+arg2]
	cmp eax, button_x
	jl button_0
	cmp eax, button_x+button_size
	jg button_0
	mov eax, [ebp+arg3]
	cmp eax, button_y
	jl button_0
	cmp eax, button_y+button_size/2+1
	jg button_0
	;s a dat click in buton
	cmp ord, 0
	je negru
	;;;;;albe
	cmp suma_albe, 15
	je aff
	mov ecx, 24
bucla_alb:
	mov edi, ecx
	cmp byte ptr[piese_albe+ecx], 1
	jb fin
	cmp zarr1, 0
	je az2
		sub edi, zarr1
		
		cmp edi, 1
		jl az2
			cmp byte ptr[piese_negre+edi], 1
			jae az2
				add ver, 1
az2:
	cmp zarr2, 0
	je fin
		mov edi, ecx
		sub edi, zarr2
		
		cmp edi, 1
		jl fin
			cmp byte ptr[piese_negre+edi], 1
			jae fin
				add ver, 1
	
fin:
	loop bucla_alb
	jmp aff
negru:
	
	cmp suma_negre, 15
	je aff
	mov ecx, 24
bucla_negre:
	mov edi, ecx
	cmp byte ptr[piese_albe+ecx], 1
	jb fin
	cmp zarr1, 0
	je naz
		add edi, zarr1
		
		cmp edi, 24
		ja naz
			cmp byte ptr[piese_albe+edi], 1
			jae naz
				add ver, 1
naz:
	cmp zarr2, 0
	je finn
		mov edi, ecx
		add edi, zarr2
		
		cmp edi, 24
		ja finn
			cmp byte ptr[piese_negre+edi], 1
			jae finn
				add ver, 1
	
finn:
	loop bucla_negre
	
aff:
	
	cmp ver, 0
	jne ac
	mov zarr1, 0
	mov zarr2, 0
	;;;;verifica daca am mutat dupa ambele zaruri
ac:
	cmp zarr1, 0
	jne button_0
	cmp zarr2, 0
	jne button_0
	mov dubla, 0;;;;;;pun in dubla zero pt ca acum dau cu zarul
		;;;;;;;;;;;;;;;;verific a cui e tura alb sau negru
	inc ord
	mov eax, ord
	mov esi, 2
	mov edx, 0
	div esi
	mov ord, edx
	;generez doua numere random de la 1 la 6 pentru zaruri
	rdtsc
	xor eax, edx
	mov edx, 0
	div zar
	add edx, 1
	mov zar1, edx
	mov zarr1, edx
	; add edx, '0'
	; make_text_macro edx, area, 350, 290
	rdtsc
	xor eax, edx
	mov edx, 0
	div zar
	add edx, 1
	mov zar2, edx
	mov zarr2, edx
	cmp  edx, zar1
	je button_1
	mov dubla, 4
	; add edx, '0'
	; make_text_macro edx, area, 330, 290
button_0:
	mov eax, [ebp+arg2]
	cmp eax, B1x+60
	jl button_1
	cmp eax, B1x+button_size+90
	jg button_1
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_1
	cmp eax, B1y+button_size*5-5
	jg button_1
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 0, dubla, zar1, suma_albe, suma_negre
	
	
button_1:	
	mov eax, [ebp+arg2]
	cmp eax, B1x
	jl button_2
	cmp eax, B1x+button_size
	jg button_2
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_2
	cmp eax, B1y+button_size*5-5
	jg button_2
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 1, dubla, zar1, suma_albe, suma_negre
	
	
button_2:	
	mov eax, [ebp+arg2]
	cmp eax, B1x-50
	jl button_3
	cmp eax, B1x+button_size-50
	jg button_3
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_3
	cmp eax, B1y+button_size*5-5
	jg button_3
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 2, dubla, zar1, suma_albe, suma_negre
	
button_3:
	mov eax, [ebp+arg2]
	cmp eax, B1x-100
	jl button_4
	cmp eax, B1x+button_size-100
	jg button_4
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_4
	cmp eax, B1y+button_size*5-5
	jg button_4
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 3, dubla, zar1, suma_albe, suma_negre


button_4:
	mov eax, [ebp+arg2]
	cmp eax, B1x-150
	jl button_5
	cmp eax, B1x+button_size-150
	jg button_5
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_5
	cmp eax, B1y+button_size*5-5
	jg button_5
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1,  zarr1, zarr2, ord, 4, dubla, zar1, suma_albe, suma_negre
	

button_5:
	mov eax, [ebp+arg2]
	cmp eax, B1x-200
	jl button_6
	cmp eax, B1x+button_size-200
	jg button_6
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_6
	cmp eax, B1y+button_size*5-5
	jg button_6
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 5, dubla, zar1, suma_albe, suma_negre
	

button_6:
	mov eax, [ebp+arg2]
	cmp eax, B1x-250
	jl button_7
	cmp eax, B1x+button_size-250
	jg button_7
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_7
	cmp eax, B1y+button_size*5-5
	jg button_7
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 6, dubla, zar1, suma_albe, suma_negre
	
button_7:
	mov eax, [ebp+arg2]
	cmp eax, B1x-390
	jl button_8
	cmp eax, B1x+button_size-390
	jg button_8
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_8
	cmp eax, B1y+button_size*5-5
	jg button_8
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 7, dubla, zar1, suma_albe, suma_negre

button_8:
	mov eax, [ebp+arg2]
	cmp eax, B1x-440
	jl button_9
	cmp eax, B1x+button_size-440
	jg button_9
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_9
	cmp eax, B1y+button_size*5-5
	jg button_9
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 8, dubla, zar1, suma_albe, suma_negre
	

button_9:
	mov eax, [ebp+arg2]
	cmp eax, B1x-490
	jl button_10
	cmp eax, B1x+button_size-490
	jg button_10
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_10
	cmp eax, B1y+button_size*5-5
	jg button_10
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1,  zarr1, zarr2, ord, 9, dubla, zar1, suma_albe, suma_negre
	
button_10:
	mov eax, [ebp+arg2]
	cmp eax, B1x-540
	jl button_11
	cmp eax, B1x+button_size-540
	jg button_11
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_11
	cmp eax, B1y+button_size*5-5
	jg button_11
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 10, dubla, zar1, suma_albe, suma_negre
	
button_11:
	mov eax, [ebp+arg2]
	cmp eax, B1x-590
	jl button_12
	cmp eax, B1x+button_size-590
	jg button_12
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_12
	cmp eax, B1y+button_size*5-5
	jg button_12
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 11, dubla, zar1, suma_albe, suma_negre
	
button_12:
	mov eax, [ebp+arg2]
	cmp eax, B1x-640
	jl button_13
	cmp eax, B1x+button_size-640
	jg button_13
	mov eax, [ebp+arg3]
	cmp eax, B1y
	jl button_13
	cmp eax, B1y+button_size*5-5
	jg button_13
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 12, dubla, zar1, suma_albe, suma_negre
	
	
button_13:;;;;;;;;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x
	jl button_14
	cmp eax, B2x+button_size
	jg button_14
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_14
	cmp eax, B2y+button_size*5-5
	jg button_14
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 13, dubla, zar1, suma_albe, suma_negre
	
button_14:;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+50
	jl button_15
	cmp eax, B2x+button_size+50
	jg button_15
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_15
	cmp eax, B2y+button_size*5-5
	jg button_15
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 14, dubla, zar1, suma_albe, suma_negre
	
	
button_15:;;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+100
	jl button_16
	cmp eax, B2x+button_size+100
	jg button_16
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_16
	cmp eax, B2y+button_size*5-5
	jg button_16
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 15, dubla, zar1, suma_albe, suma_negre
	
	
	
button_16:;;;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+150
	jl button_17
	cmp eax, B2x+button_size+150
	jg button_17
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_17
	cmp eax, B2y+button_size*5-5
	jg button_17
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 16, dubla, zar1, suma_albe, suma_negre
	

button_17:;;;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+200
	jl button_18
	cmp eax, B2x+button_size+200
	jg button_18
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_18
	cmp eax, B2y+button_size*5-5
	jg button_18
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 17, dubla, zar1, suma_albe, suma_negre
	

button_18:;;;;;;;;;;;;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+250
	jl button_19
	cmp eax, B2x+button_size+250
	jg button_19
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_19
	cmp eax, B2y+button_size*5-5
	jg button_19
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 18, dubla, zar1, suma_albe, suma_negre
	

button_19:;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+390
	jl button_20
	cmp eax, B2x+button_size+390
	jg button_20
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_20
	cmp eax, B2y+button_size*5-5
	jg button_20
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 19, dubla, zar1, suma_albe, suma_negre
	
	

button_20:;;;;;;;;;
mov eax, [ebp+arg2]
	cmp eax, B2x+440
	jl button_21
	cmp eax, B2x+button_size+440
	jg button_21
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_21
	cmp eax, B2y+button_size*5-5
	jg button_21
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 20, dubla, zar1, suma_albe, suma_negre
	

button_21:;;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+490
	jl button_22
	cmp eax, B2x+button_size+490
	jg button_22
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_22
	cmp eax, B2y+button_size*5-5
	jg button_22
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 21, dubla, zar1, suma_albe, suma_negre
	
button_22:
	mov eax, [ebp+arg2]
	cmp eax, B2x+540
	jl button_23
	cmp eax, B2x+button_size+540
	jg button_23
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_23
	cmp eax, B2y+button_size*5-5
	jg button_23
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 22, dubla, zar1, suma_albe, suma_negre
	

button_23:;;;;;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+590
	jl button_24
	cmp eax, B2x+button_size+590
	jg button_24
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_24
	cmp eax, B2y+button_size*5-5
	jg button_24
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 23, dubla, zar1, suma_albe, suma_negre
	
button_24:;;;;;;;;;;;;;
mov eax, [ebp+arg2]
	cmp eax, B2x+640
	jl button_25
	cmp eax, B2x+button_size+640
	jg button_25
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_25
	cmp eax, B2y+button_size*5-5
	jg button_25
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 24, dubla, zar1, suma_albe, suma_negre
	
	
button_25:;;;;;;;;;;;;;
	mov eax, [ebp+arg2]
	cmp eax, B2x+700
	jl button_fail
	cmp eax, B2x+button_size+700+30
	jg button_fail
	mov eax, [ebp+arg3]
	cmp eax, B2y
	jl button_fail
	cmp eax, B2y+button_size*5-5
	jg button_fail
	
	veficare  piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, 25, dubla, zar1, suma_albe, suma_negre
	
	
afara_albe:
	jmp afisare_litere
button_fail:
	
	jmp afisare_litere
	
	
evt_timer:
	inc counter
afisare_litere:
	;codul de mai jos initializeaza fereastra cu o culoare anume(galben)
	mov edi, area
	mov ecx, area_height
	mov ebp, 0FFD700h
bucla_liniii:
	mov eax, 0FFD700h
	push ecx
	mov ecx, area_width
bucla_coloanee:
	mov [edi], eax
	add edi, 4
	loop bucla_coloanee
	pop ecx
	loop bucla_liniii
	
	;afisez zar1
	mov edx, zar1
	add edx, '0'
	make_zar_macro edx, area, 350, 290
	;afisez zar2
	mov edx, zar2
	add edx, '0'
	make_zar_macro edx, area, 310, 290	
	
	suma_vectori piese_albe, piese_negre, suma_albe, suma_negre;;;;;aflu cate piese se afla atat in casa la nnegre cat si la albe
	
	patrat 0, 0, 20, 1000, 8B4513h
	patrat 0, 580, 20, 1000, 8B4513h
	patrat 0, 0, 600, 100, 8B4513h
	patrat 790, 0, 600, 210, 8B4513h
	patrat 400, 0, 600, 90, 8B4513h
	patrat 445, 0, 600, 1, 0
	patrat 489, 20, 561, 1, 0
	patrat 401, 20, 561, 1, 0
	patrat 99, 20, 561, 1, 0
	patrat 791, 20, 561, 1, 0
	patrat 99, 19, 1, 303, 0
	patrat 489, 581, 1, 303, 0
	patrat 99, 581, 1, 303, 0
	patrat 489, 19, 1, 303, 0
	
	cmp ord, 0
	jne rand_alb
	cmp zarr1, 0
	jne acc
	cmp zarr2, 0
	je ex	
acc:
	make_text_macro 'N', area, 590, 290
	make_text_macro 'E', area, 600, 290
	make_text_macro 'G', area, 610, 290
	make_text_macro 'R', area, 620, 290
	make_text_macro 'U', area, 630, 290
	jmp ex
rand_alb:
	cmp zarr1, 0
	jne acc2
	cmp zarr2, 0
	je ex
acc2:
	make_text_macro 'A', area, 600, 290
	make_text_macro 'L', area, 610, 290
	make_text_macro 'B', area, 620, 290


ex:
	
	make_text_macro 'R', area, 795, 290
	make_text_macro 'O', area, 805, 290
	make_text_macro 'L', area, 815, 290
	make_text_macro 'L', area, 825, 290
	patrat 791, 289, 4, 48, 0696969h
	patrat 791, 309, 4, 48, 0696969h
	patrat 791, 289, 24, 4, 0696969h
	patrat 835, 289, 24, 4, 0696969h
	patrat 791, 288, 1, 48, 0
	patrat 791, 313, 2, 48, 0
	patrat 790, 289, 24, 1, 0
	patrat 839, 289, 24, 1, 0
	
	;sus dreapta
	patrat 100, 20, 5, 50, 0B22222h
	patrat 150, 20, 5, 50, 0696969h
	patrat 200, 20, 5, 50, 0B22222h
	patrat 250, 20, 5, 50, 0696969h
	patrat 300, 20, 5, 50, 0B22222h
	patrat 350, 20, 5, 50, 0696969h
	;sus stanga
	patrat 490, 20, 5, 50, 0B22222h
	patrat 540, 20, 5, 50, 0696969h
	patrat 590, 20, 5, 50, 0B22222h
	patrat 640, 20, 5, 50, 0696969h
	patrat 690, 20, 5, 50, 0B22222h
	patrat 740, 20, 5, 50, 0696969h
	;jos stanga
	patrat 100, 575, 5, 50, 0696969h
	patrat 150, 575, 5, 50, 0B22222h
	patrat 200, 575, 5, 50, 0696969h
	patrat 250, 575, 5, 50, 0B22222h
	patrat 300, 575, 5, 50, 0696969h
	patrat 350, 575, 5, 50, 0B22222h
	;jos dreapta
	patrat 490, 575, 5, 50, 0696969h
	patrat 540, 575, 5, 50, 0B22222h
	patrat 590, 575, 5, 50, 0696969h
	patrat 640, 575, 5, 50, 0B22222h
	patrat 690, 575, 5, 50, 0696969h
	patrat 740, 575, 5, 50, 0B22222h
	
	
	triunghi 101, 25, 0B22222h
	triunghi 151, 25, 0696969h
	triunghi 201, 25, 0B22222h
	triunghi 251, 25, 0696969h
	triunghi 301, 25, 0B22222h
	triunghi 351, 25, 0696969h
	
	triunghi 491, 25, 0B22222h
	triunghi 541, 25, 0696969h
	triunghi 591, 25, 0B22222h
	triunghi 641, 25, 0696969h
	triunghi 691, 25, 0B22222h
	triunghi 741, 25, 0696969h
	
	triunghi_jos 101, 565, 0696969h
	triunghi_jos 151, 565, 0B22222h
	triunghi_jos 201, 565, 0696969h
	triunghi_jos 251, 565, 0B22222h
	triunghi_jos 301, 565, 0696969h
	triunghi_jos 351, 565, 0B22222h
	
	triunghi_jos 491, 565, 0696969h
	triunghi_jos 541, 565, 0B22222h
	triunghi_jos 591, 565, 0696969h
	triunghi_jos 641, 565, 0B22222h
	triunghi_jos 691, 565, 0696969h
	triunghi_jos 741, 565, 0B22222h
	
	;;;locul unde scot
	;;;;;;;;;albe
	patrat 800, 335, 245, 80, 0D2691Eh
	patrat 800, 333, 249, 2, 0
	patrat 878, 333, 249, 2, 0
	patrat 800, 333, 2, 80, 0
	patrat 800, 580, 2, 80, 0
	;;;;;;;;negree
	patrat 800, 20, 245, 80, 0D2691Eh
	patrat 800, 18, 249, 2, 0
	patrat 878, 18, 249, 2, 0
	patrat 800, 18, 2, 80, 0
	patrat 800, 265, 2, 80, 0
		

	;piese albe sus
	piese_albe_sus_vector 105, 20, piese_albe, 13
	piese_albe_sus_vector 495, 20, piese_albe, 19
	;piese negre sus
	piese_negre_sus_vector 105, 20, piese_negre, 13
	piese_negre_sus_vector 495, 20, piese_negre, 19
	;piese albe jos
	piese_albe_jos_vector 745, 540, piese_albe, 1
	piese_albe_jos_vector 355, 540, piese_albe, 7
	;piese negre jos
	piese_negre_jos_vector 745, 540, piese_negre, 1
	piese_negre_jos_vector 355, 540, piese_negre, 7
	
	;;piese albe mancate
	piese_mancate_albe_vector 447, 250, piese_albe, 25
	piese_mancate_negre_vector 402, 250, piese_negre, 0
	
	;;;piese scoase
	piese_albe_scoase 820, 540, piese_albe, 0
	piese_mancate_negre_vector 820, 20, piese_negre, 25
	
	
	
final_draw:
	popa
	mov esp, ebp
	pop ebp
	ret
draw endp


start:
	;alocam memorie pentru zona de desenat
	mov eax, area_width
	mov ebx, area_height
	mul ebx
	shl eax, 2
	push eax
	call malloc
	add esp, 4
	mov area, eax
	;apelam functia de desenare a ferestrei
	; typedef void (*DrawFunc)(int evt, int x, int y);
	; void __cdecl BeginDrawing(const char *title, int width, int height, unsigned int *area, DrawFunc draw);
	push offset draw
	push area
	push area_height
	push area_width
	push offset window_title
	call BeginDrawing
	add esp, 20
	
	;terminarea programului
	push 0
	call exit
end start

.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;includem biblioteci, si declaram ce functii vrem sa importam
includelib msvcrt.lib
extern exit: proc
extern printf: proc
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;declaram simbolul start ca public - de acolo incepe executia
public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;sectiunile programului, date, respectiv cod
.data
;aici declaram date
sir dw 10, 11, 7, 6, 25, 4, 56, 2
n dd $-sir
format db "%d ", 0
.code
start:
	;aici se scrie codul
	mov eax, n
	sub eax, 2
	mov n, eax
	mov eax, 0
	mov esi, 0
	mov ecx, 2
	
	
	bucla:
		bucla2:
			mov dx, sir[ecx]
			cmp sir[esi], dx
			jae et
			mov ax, sir[esi]
			mov sir[ecx], ax
			mov sir[esi], dx
			et:
			add ecx, 2
			cmp ecx, n
			jbe bucla2
			
		add esi, 2
		mov ecx, esi
		
		cmp esi, n
		jb bucla
		
	add n, 2
	mov esi, 0
	mov eax, 0
	af:
		mov ax, sir[esi]
		push eax
		push offset format
		call printf
		add esp, 8
		
		add esi, 2
	cmp esi, n
	jb af
	
	
	;terminarea programului
	push 0
	call exit
end start


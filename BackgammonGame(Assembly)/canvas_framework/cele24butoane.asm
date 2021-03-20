veficare macro piese_albe, piese_negre, clickalb, clicknegru, click1, zarr1, zarr2, ord, nrbt, dubla, zar1, suma_albe, suma_negre
local b2n, b2, et2a, urma, eta2, vera2, veraa2, compa2, compaa2, ad2, et2, veran2,afisare_l, verana2, ett2, dubla_alb, dubla_negru, conta, continue2, inn3, inn4, nr;
local scot_albe, afara, compaan2,inita,  scot_negre, initnegru, contn, icia, icia1, initalb, compan2, puna1, ur, ia1, ia2, puna2, punn1, punn2, et, et1,ia, inn, afaraa, afaraalb, init, urmn, continue, scot_albe2, scot_negre2;
	mov edi, nrbt
	cmp zarr1, 0;;;;verifica daca mai pot muta (adica nu am mutat deja)
	je et
		jmp et1
et:
	cmp zarr2, 0
	je afara
et1:
	mov esi, zar1
	cmp zarr1, 0
	jne afisare_l
		cmp dubla, 2
		jae afisare_l
			inc dubla
			mov zarr1, esi
			mov zarr2, esi
			jmp afisare_l
afisare_l:
	cmp ord, 0
	je b2n
	
	cmp byte ptr [piese_albe+25], 0
	jne afaraalb
		mov ah, byte ptr[piese_negre+edi]
		mov al, byte ptr[piese_albe+edi]
		cmp al, 0
		je b2
			cmp clickalb, 1
			je et2a
b2:
	cmp clickalb, 2
	je eta2
		jmp afara
et2a:
	;;;verific daca pot muta piesa pe care dau click
	mov ebx, zarr1
	cmp ebx, zar1
	jne vera2
		cmp zarr1, 0
		je vera2
		
			mov esi, edi
			sub esi, zar1
			
			cmp esi, 1;;;verific daca  poz curenta scazuta cu zar 1 e mai mica decat 1
			jl scot_albe
				cmp byte ptr[piese_negre+esi], 1
				ja vera2
					jmp veraa2
scot_albe:
	cmp suma_albe, 15
	je veraa2
vera2:
	mov ebx, zarr2
	cmp ebx, zar2
	jne afara
		cmp zarr2, 0
		je afara
		
			mov esi, edi
			sub esi, zar2
			
			cmp esi, 1
			jl scot_albe2
				jmp conta
scot_albe2:
	cmp suma_albe, 15
	jne afara
		je veraa2

conta:

	cmp byte ptr[piese_negre+esi], 1
	ja afara
veraa2:;;;
	cmp edi, 0;;;;;;daca am dat click pe 0 nu pot sa mai iau piesa de acolo si ma scoate afara
	je afara
	
	dec al
	mov click1, edi
	mov byte ptr[piese_albe+edi], al
	inc clickalb
	jmp afara
	
eta2:
	cmp suma_albe, 14
	jne continue
				;;;;;;;;pt scos din casaaaa
				cmp edi, 0
				jne continue
					cmp suma_albe, 14
					jne continue
						mov esi, click1
						sub esi, zarr1
						cmp esi, 1
						jl ia1
						jmp ur
				ia1:
					mov zarr1, 0
					jmp ia
						ur:
							mov esi, click1
							sub esi, zarr2
							cmp esi, 1
							jl ia2
					jmp continue
				ia2:
					mov zarr2, 0
					jmp ia
				;;;;;;;;;;;;;;;;;;;								
continue:		
		cmp edi, 0
		je afara
	;;;;;;;;;;verific daca pot muta piesa selectata
	cmp byte ptr[piese_negre+edi],1
	ja afara
		mov edx, click1
		sub edx, zar1
		cmp edx, edi
		jne compa2
			jmp compaa2
compa2:
	mov edx, click1
	sub edx, zar2
	cmp edx, edi
	jne afara
compaa2:;;;;;;;;;;;;
	mov esi, nrbt
	add esi, zarr1
	cmp esi, click1
	jne puna1
		mov zarr1,0
		jmp puna2
puna1:
	mov esi, nrbt
	add esi, zarr2
	cmp esi, click1
	jne afara
		mov zarr2, 0
puna2:
	cmp ah, 1;;;;;;scad piesa neagra daca e una 
	jne ia
		dec ah
		mov byte ptr[piese_negre+edi], ah
		mov ah, byte ptr[piese_negre+0]
		inc ah
		mov byte ptr[piese_negre+0], ah
ia:
	cmp edi, 0
	jne icia
		cmp suma_albe, 14
		jne afara
	
icia:
	inc al
	mov clickalb, 1
	mov byte ptr[piese_albe+edi], al
	jmp afara
afaraalb:;;;;;;;cazul in care am piesa mancata
	; cmp zarr1, 0
	; je urma;;;;;aca zarr1 este 0 atunci verific cu zar 2
	mov esi, 25
	sub esi, zarr1
	cmp esi, 25
	je urma
		cmp byte ptr[piese_negre+esi], 1
		ja urma
			cmp edi, esi
			jne urma
				mov al, byte ptr[piese_albe+esi]
				inc al
				mov byte ptr[piese_albe+esi], al
				mov al, byte ptr[piese_albe+25]
				dec al
				mov byte ptr[piese_albe+25], al
				
				mov zarr1, 0
				
				cmp byte ptr [piese_negre+esi], 1
				jne afara
				mov byte ptr[piese_negre+esi], 0
				mov ah, byte ptr[piese_negre+0]
				inc ah
				mov byte ptr[piese_negre+0], ah
				jmp afara
urma: 
	;mov zarr1, 0
	mov esi, 25
	sub esi, zarr2
	cmp esi, 25
	je inita
		cmp byte ptr[piese_negre+esi], 1
		ja inita
			cmp edi, esi
			jne afara
				mov al, byte ptr[piese_albe+esi]
				inc al
				mov byte ptr[piese_albe+esi], al
				mov al, byte ptr[piese_albe+25]
				dec al
				mov byte ptr[piese_albe+25], al
				
				mov zarr2, 0
				
				cmp byte ptr [piese_negre+esi], 1
				jne afara
				
				mov byte ptr[piese_negre+esi], 0
				mov ah, byte ptr[piese_albe+0]
				inc ah
				
				mov byte ptr[piese_negre+0], ah
				jmp afara
inita:
	mov esi, 25
	sub esi, zarr1
	cmp esi, 25
	je initalb
		cmp byte ptr[piese_negre+esi], 1
		ja initalb
		jmp afara
initalb:
	mov zarr1, 0
	mov zarr2, 0
	jmp afara
	;;;;;;;;;;;TREC SA VERIFIC PIESELE NEGRE;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
b2n:
	cmp byte ptr [piese_negre+0], 0
	jne afaraa;;;daca am piesa mancataaaaaaaaaaaaaaaaaaaaaaaaaa
	mov ah, byte ptr[piese_albe+edi]
	mov al, byte ptr[piese_negre+edi]
	cmp al, 0
	je ad2
		cmp clicknegru, 1
		je et2
ad2:
	cmp clicknegru, 2
	je ett2
	jmp afara
et2:
	;;;verific daca pot muta piesa pe care dau click pe negru
	mov ebx, zarr1
	cmp ebx, zar1
	jne veran2
		cmp zarr1, 0
		je veran2
			mov esi, edi
			add esi, zar1
			
			cmp esi, 24
			ja scot_negre
				cmp byte ptr[piese_albe+esi], 1
				ja veran2
					jmp verana2
scot_negre:
	cmp suma_negre,15	
	je verana2
veran2:
	mov ebx, zarr2
	cmp ebx, zar2
	jne afara
		cmp zarr2, 0
		je afara
		
			mov esi, edi
			add esi, zar2 
			
			cmp esi, 24
			ja scot_negre2
				jmp contn
scot_negre2:
	cmp suma_negre,15
	jne afara	
		jmp verana2
contn:
	cmp byte ptr[piese_albe+esi], 1
	ja afara
verana2:;;;
	cmp edi, 25;;;;;;daca am dat click pe 25 nu pot sa mai iau piesa de acolo si ma scoate afara
	je afara
	
	dec al
	mov click1, edi
	mov byte ptr[piese_negre+edi], al
	inc clicknegru
	jmp afara
ett2:
	cmp suma_negre, 14
	jne continue2
				;;;;;;;;pt scos din casaaaa
				cmp edi, 25
				jne continue2
					cmp suma_negre,14
					jne continue2
						mov esi, click1
						add esi, zarr1
						cmp esi, 24
						ja inn3
						jmp nr
				inn3:
					mov zarr1, 0
					jmp inn
						nr:
							mov esi, click1
							add esi, zarr2
							cmp esi, 24
							ja inn4
					jmp continue2
				inn4:
					mov zarr2, 0
					jmp inn
				;;;;;;;;;;;;;;;;;;;;;;;
continue2:				
		cmp edi, 25
		je afara
	;;;;;;;;;;verific daca pot muta piesa selectata
	cmp byte ptr[piese_albe+edi],1
	ja afara
	mov edx, click1
	add edx, zar1
	cmp edx, edi
	jne compan2
	jmp compaan2
compan2:
	mov edx, click1
	add edx, zar2
	cmp edx, edi
	jne afara
compaan2:;;;;;;;;;;;;
	
	mov esi, nrbt
	sub esi, zarr1
	cmp esi, click1
	jne punn1
	mov zarr1,0
	jmp punn2
punn1:
	mov esi, nrbt
	sub esi, zarr2
	cmp esi, click1
	jne afara
	mov zarr2, 0
punn2:
	cmp ah, 1;;;;;;scad piesa neagra daca e una 
	jne inn
	dec ah
	mov byte ptr[piese_albe+edi], ah
	mov ah, byte ptr[piese_albe+25]
	inc ah
	mov byte ptr[piese_albe+25], ah
inn:
	cmp edi, 25
	jne icia1
		cmp suma_negre, 14
		jne afara
icia1:
	inc al
	mov clicknegru, 1
	mov byte ptr[piese_negre+edi], al
	jmp afara
afaraa:
	mov esi, zarr1
	cmp esi, 0
	je urmn
	cmp byte ptr[piese_albe+esi], 1
	ja urmn
		cmp edi, zarr1
		jne urmn
		
			mov al, byte ptr[piese_negre+esi]
			inc al
			mov byte ptr[piese_negre+esi], al
			mov al, byte ptr[piese_negre+0]
			dec al
			mov byte ptr[piese_negre+0], al
			mov zarr1, 0
			cmp byte ptr [piese_albe+esi], 1
			jne afara
				mov byte ptr[piese_albe+esi], 0
				mov ah, byte ptr[piese_albe+25]
				inc ah
				mov byte ptr[piese_albe+25], ah
					jmp afara
urmn:
	;mov zarr1, 0
	mov esi, zarr2
	cmp esi, 0
	je init
	cmp byte ptr[piese_albe+esi], 1
	ja init
		cmp edi, zarr2
		jne afara
		
			mov al, byte ptr[piese_negre+esi]
			inc al
			mov byte ptr[piese_negre+esi], al
			mov al, byte ptr[piese_negre+0]
			dec al
			mov byte ptr[piese_negre+0], al
			mov zarr2, 0
			cmp byte ptr [piese_albe+esi], 1
			jne afara
				mov byte ptr[piese_albe+esi], 0
				mov ah, byte ptr[piese_albe+25]
				inc ah
				mov byte ptr[piese_albe+25], ah
					jmp afara
init:
	mov esi, 0
	add esi, zarr1
	cmp esi, 0
	je initnegru
		cmp byte ptr[piese_albe+esi], 1
		ja initnegru
		jmp afara
initnegru:
	mov zarr1, 0
	mov zarr2, 0
	jmp afara
afara:
endm
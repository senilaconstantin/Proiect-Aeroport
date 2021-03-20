; un macro ca sa apelam mai usor desenarea simbolului
make_text_macro macro symbol, drawArea, x, y
	push y
	push x
	push drawArea
	push symbol
	call make_text
	add esp, 16
endm
; un macro ca sa apelam mai usor desenarea piesei
make_piesa_macro macro symbol, drawArea, x, y
	push y
	push x
	push drawArea
	push symbol
	call make_piesa
	add esp, 16
endm

;;; un macro ca sa apelam mai usor afisarea zarurilor
make_zar_macro macro symbol, drawArea, x, y
	push y
	push x
	push drawArea
	push symbol
	call make_zar
	add esp, 16
endm

;un macrou pt partea stanfga sus afisare piesei
;afisare sus piese albe
piese_albe_sus_vector macro xx, yy, piese, p
local et, af, eticheta1
	mov x, xx
	lea esi, piese
	add esi, p
	mov eax, esi
	add eax, 5
et:
	mov y, yy
	mov cl, byte ptr[esi]
	cmp cl, 0
	je af
eticheta1:
    make_piesa_macro 'b', area, x, y
	add y, 30
	loop eticheta1
af:
	add x, 50
	inc esi
	cmp esi, eax
	jbe et
endm
;afisare sus piese negre
piese_negre_sus_vector macro xx, yy, piese, p
local et, af, eticheta1
	mov x, xx
	lea esi, piese
	add esi, p
	mov eax, esi
	add eax, 5
et:
	mov y, yy
	mov cl, byte ptr[esi]
	cmp cl, 0
	je af
eticheta1:
    make_piesa_macro 'a', area, x, y
	add y, 30
	loop eticheta1
af:
	add x, 50
	inc esi
	cmp esi, eax
	jbe et
endm

;afisare jos piese albe
piese_albe_jos_vector macro xx, yy, piese, p
local et, af, eticheta1
	mov x, xx
	lea esi, piese
	add esi, p
	mov eax, esi
	add eax, 5
et:
	mov y, yy
	mov cl, byte ptr[esi]
	cmp cl, 0
	je af
eticheta1:
    make_piesa_macro 'b', area, x, y
	sub y, 30
	loop eticheta1
af:
	sub x, 50
	inc esi
	cmp esi, eax
	jbe et
endm

;afisare jos piese negre
piese_negre_jos_vector macro xx, yy, piese, p
local et, af, eticheta1
	mov x, xx
	lea esi, piese
	add esi, p
	mov eax, esi
	add eax, 5
et:
	mov y, yy
	mov cl, byte ptr[esi]
	cmp cl, 0
	je af
eticheta1:
    make_piesa_macro 'a', area, x, y
	sub y, 30
	loop eticheta1
af:
	sub x, 50
	inc esi
	cmp esi, eax
	jbe et
endm
;piese mancate 
piese_mancate_albe_vector macro xx, yy, piese, p
local etm, afm, eticheta1m
	mov x, xx
	lea esi, piese
	add esi, p
	mov eax, esi
	add eax, 5
etm:
	mov y, yy
	mov cl, byte ptr[esi]
	cmp cl, 0
	je afm
eticheta1m:
    make_piesa_macro 'b', area, x, y
	add y, 14
	loop eticheta1m
afm:
	
endm
;mancate negre
piese_mancate_negre_vector macro xx, yy, piese, p
local etmn, afmn, eticheta1mn, etmnn
	mov x, xx
	lea esi, piese
	add esi, p
	mov eax, esi
	add eax, 5
etmnn:
	mov y, yy
	mov cl, byte ptr[esi]
	cmp cl, 0
	je afmn
eticheta1mn:
    make_piesa_macro 'a', area, x, y
	add y, 14
	loop eticheta1mn
afmn:
	
endm
;;;;;piese albe scoase
piese_albe_scoase macro xx, yy, piese, p
local et, af, eticheta1
	mov x, xx
	lea esi, piese
	add esi, p
	mov eax, esi
	add eax, 5
	mov y, yy
	mov cl, byte ptr[esi]
	cmp cl, 0
	je af
eticheta1:
    make_piesa_macro 'b', area, x, y
	sub y, 14
	loop eticheta1
af:
	
endm

;;;;;;aflu suma de piese din casa la albe cat si la negre
suma_vectori macro piese_albe, piese_negre, suma_albe, suma_negre
	mov edx, 19
	mov eax, 0
	mov suma_albe, 0
	mov suma_negre, 0
	mov ecx, 7
suma:
	mov al, byte ptr [piese_albe+esi]
	add suma_albe, eax; ;;;;;fac suma in casa pentru cele albe
	inc esi
	mov al, byte ptr [piese_negre+edx]
	add suma_negre, eax;;;;;;;;;;fac suma din casa pentru cele negre
	inc edx
	loop suma;;;;;;fac suma din casa atat pt albe  cat si pt negre
	; push suma_negre
	; push offset format
	; call printf
	; add esp, 8
endm 

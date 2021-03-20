	;linie orizontala
line_horizontal macro x, y, len, color 
local bucla_line
	mov eax, y  ;eax=y
	mov ebx, area_width
	mul ebx ;eax=y*area_width
	add eax, x   ;eax=y*area_width+x
	shl eax, 2   ;eax=(y*area_width+x)+4
	add eax, area
	mov ecx, len
bucla_line:
	mov dword ptr[eax], color
	add eax, 4
	loop bucla_line
endm

	;linie verticala
line_vertical macro x, y, len, color
local bucla_line
	mov eax, y  ;eax=y
	mov ebx, area_width
	mul ebx ;eax=y*area_width
	add eax, x   ;eax=y*area_width+x
	shl eax, 2   ;eax=(y*area_width+x)+4
	add eax, area
	mov ecx, len
bucla_line:
	mov dword ptr[eax], color
	add eax, area_width*4
	loop bucla_line
endm

patrat macro x, y, lat, lung, color
local blinie
local afara
	mov ebx, 0
	mov eax, y
	mov ecx, lat
blinie:
	push eax
	push ecx
	line_horizontal x, eax, lung, color
	pop ecx
	pop eax
	add ebx, 1
	add eax, 1
	cmp ebx, lat
	je afara
	loop blinie
afara:
endm

triunghi macro x, y, culoare
local triu
	mov ecx, 24
	mov esi, 48
	mov edi, x
	mov eax, y
	
	triu:
	push edi
	push eax
	push ecx
	patrat edi, eax, 10, esi, culoare
	pop ecx
	pop eax
	pop edi
	add eax, 10
	add edi, 1
	;mov esi, ecx
	sub esi, 2
	loop triu
endm

triunghi_jos macro x, y, culoare
local triu
	mov ecx, 24
	mov esi, 48
	mov edi, x
	mov eax, y
	
	triu:
	push edi
	push eax
	push ecx
	patrat edi, eax, 10, esi, culoare
	pop ecx
	pop eax
	pop edi
	sub eax, 10
	add edi, 1
	;mov esi, ecx
	sub esi, 2
	loop triu
endm
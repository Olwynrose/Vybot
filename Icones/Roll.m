
clc
clear all


d = 7;
n_max = 60;
pos1i = 200;
pos1j = 200;
pos2i = 200;
pos2j = 200;
dbl = 15;
alpha = 0.02;
move = 0.003;
nom = 'RollG.gif';
dim = 2*d+1;

Im =  im2double( imread('dallefond.png') );
idim = size(Im, 1);
jdim = size(Im, 2);

x = [1];
y = [1];

col = 0.7*[0.1, 0.8, 0.05];

G = zeros(dim, dim, 3);

for i=-d:d
    for j=-d:d
        if (i*i+j*j)<d*d
            G(i+d+1,j+d+1,:) = max(0, min(1,col*exp(-(i*i+j*j)/(0.25*d*d)) ));
        end
    end
end

pos1i = pos1i -d-1;
pos1j = pos1j -d-1;
pos2i = pos2i -d-1;
pos2j = pos2j -d-1;

N = n_max;
for k=1:dbl
    a(k) = floor(1+8*rand());
    b(k) = floor(1+8*rand());
    c(k) = floor(1+8*rand());
    e(k) = floor(1+8*rand());
    aa(k) = floor(1+8*rand());
    bb(k) = floor(1+8*rand());
    cc(k) = floor(1+8*rand());
    ee(k) = floor(1+8*rand());
end
for n=1:N
    
    for k=1:dbl
        h(k,1) = floor( (idim*move)*(sin(a(k)*2*pi*n/n_max + 2*k*pi/dbl)+cos(b(k)*2*pi*n/n_max + 2*k*pi/dbl) )+ pos1i );
        l(k,1) = floor( (jdim*move)*(sin(c(k)*2*pi*n/n_max + 2*k*pi/dbl)+cos(e(k)*2*pi*n/n_max + 2*k*pi/dbl) ) + pos1j );
        h(k,2) = floor( (idim*move)*(sin(aa(k)*2*pi*n/n_max + 2*k*pi/dbl)+cos(bb(k)*2*pi*n/n_max + 2*k*pi/dbl) ) + pos2i );
        l(k,2) = floor( (jdim*move)*(sin(cc(k)*2*pi*n/n_max + 2*k*pi/dbl)+cos(ee(k)*2*pi*n/n_max + 2*k*pi/dbl) ) + pos2j );
    end  
    
    Im_out = Im;
    
    for i=1:dim
        for j=1:dim
            for k=1:dbl
                Im_out(i+h(k,1), j+l(k,1), :) = Im_out(i+h(k,1), j+l(k,1), :) + (1-Im_out(i+h(k,1), j+l(k,1), :)).*G(i,j,:);%*(n/N);
                Im_out(i+h(k,2), j+l(k,2), :) = Im_out(i+h(k,2), j+l(k,2), :) + (1-Im_out(i+h(k,2), j+l(k,2), :)).*G(i,j,:);%*(n/N);
            end
        end
    end
    if strcmp(nom, 'Open.gif') && (3*n/N)<1
        Im_out = Im;
    
        for i=1:dim
            for j=1:dim
                for k=1:dbl
                    Im_out(i+h(k,1), j+l(k,1), :) = Im_out(i+h(k,1), j+l(k,1), :) + (1-Im_out(i+h(k,1), j+l(k,1), :)).*G(i,j,:)*(3*n/N);
                    Im_out(i+h(k,2), j+l(k,2), :) = Im_out(i+h(k,2), j+l(k,2), :) + (1-Im_out(i+h(k,2), j+l(k,2), :)).*G(i,j,:)*(3*n/N);
                end
            end
        end
    end
    
    iidim = idim;
    jjdim = floor(1.3*iidim);
  
    %Im_out = max(0, min(1, imresize(Im_out, [100, 100]) ));
    Im_out = Im_out(190:210,190:210,:);
    
%     rapp = jdim/idim;
%     iidim = 100;
%     ijdim = floor(iidim*rapp);
%     Im_buf = max(0, min(1, imresize(Im_out, [iidim, ijdim]) ));
%     Im_out = zeros(100, 130, 3);
%     Im_out(:, floor((130-ijdim)/2)+1:floor((130-ijdim)/2)+ijdim,:) = Im_buf;
    
    
    if n==1
        [A,map] = rgb2ind(Im_out,256); 
        imwrite(A,map,nom,'gif','LoopCount',Inf,'DelayTime',0.1);
    else
        [A,map] = rgb2ind(Im_out,256); 
        imwrite(A,map,nom,'gif','WriteMode','append','DelayTime',0.1);
    end
    
    
    figure(17);
    hold off,
    imagesc(Im_out);
    pause(0.05);
end
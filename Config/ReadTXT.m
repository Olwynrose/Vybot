%% Show image saved in a file
clear all
%
% Java code to write the image
%  BufferedWriter writer = new BufferedWriter(new FileWriter("tmp.txt"));
%  writer.write(Integer.toString(idim));
%  writer.newLine();
%  writer.flush();
%  writer.write(Integer.toString(jdim));
%  writer.newLine();
%  writer.flush();
%  for(i=0;i<idim;i++)
%  {
%      for(j=0;j<jdim;j++)
%      {
%          for(k=0;k<3;k++)
%          {
%               writer.write(Integer.toString(Im[i][j][k]));
%               writer.newLine();
%               writer.flush();
%          }
%      }
%  }
%  writer.close();
%  System.out.println("ok");
%  Thread.sleep(17000); 

fileID = fopen('Im_InvVie.txt','r');
fig = 15;
fileID = fopen('tmp.txt','r');
fig = 16;
%Define the format of the data to read. Use the string, '%f', to specify floating-point numbers.

formatSpec = '%f';

%Read the file data, filling output array, A, in column order. fscanf reapplies the format, formatSpec, throughout the file.

A = fscanf(fileID,formatSpec);

idim = A(1);
jdim = A(2);
Im = zeros(idim, jdim, 3);
n=2;
if size(A)<3*idim*jdim
    for i=1:idim
        for j=1:jdim
            for k=1:1
                n=n+1;
                Im(i,j,k) = A(n);
            end
        end
    end


    %Im = Im.*(Im>0.1);

    figure(16);
    hold off,
    imagesc(Im(:,:,1));
    %coul = 255*sum(sum(Im,1),2)./sum(sum(Im>0,1),2)


 Im1 = im2double( imread('Im_InvChat.png') );
%     figure(15);
%     hold off,
%     imagesc(Im1);
else
    for i=1:idim
        for j=1:jdim
            for k=1:3
                n=n+1;
                Im(i,j,k) = A(n)/255;
            end
        end
    end


    %Im = Im.*(Im>0.1);

    figure(fig);
    hold off,
    imagesc(Im);
    coul = 255*sum(sum(Im,1),2)./sum(sum(Im>0,1),2)


%    Im1 = im2double( imread('Im_buff.png') );
%     figure(15);
%     hold off,
%     imagesc(Im1);   
end
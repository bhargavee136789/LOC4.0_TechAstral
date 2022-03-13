from django.shortcuts import render
from rest_framework import generics, status, views, permissions
from .serializers import RegisterSerializer, EventDetailSerializer,FundingSerializer,CrowdFundingDetailSerializer,LoginSerializer,ngo_admindata, admindata,NGORegisterSerializer, NGOLoginSerializer,NGODetailSerializer
from rest_framework.response import Response
from rest_framework_simplejwt.tokens import RefreshToken
from .models import *
# from .
from rest_framework.views import APIView
from .utils import Util
from django.contrib.sites.shortcuts import get_current_site
from django.urls import reverse
from .renders import UserRenderer
import jwt
from .serializers import ReviewSerializer
from django.conf import settings
from django.core.mail import EmailMessage
import os
from rest_framework.decorators import api_view
from django.conf import settings
from drf_yasg.utils import swagger_auto_schema
from drf_yasg import openapi
# Create your views here.
# class CustomRedirect(HttpResponsePermanentRedirect):
#  	allowed_schemes = [os.environ.get('APP_SCHEME'), 'http', 'https']
class RegisterView(generics.GenericAPIView):

    serializer_class = RegisterSerializer
    renderer_classes = (UserRenderer,)

    def post(self, request):
        user = request.data
        serializer = self.serializer_class(data=user)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        data = admindata(data=request.data,context={"request":request})
        data.is_valid(raise_exception=True)
        data.save()
        user_data = serializer.data
        user = User.objects.get(email=user_data['email'])
        token = RefreshToken.for_user(user).access_token
        print(token)        	
        return Response(user_data, status=status.HTTP_201_CREATED)

class LoginAPIView(generics.GenericAPIView):
	serializer_class = LoginSerializer
	def post(self, request):
		serializer = self.serializer_class(data=request.data)
		serializer.is_valid(raise_exception=True)
		return Response(serializer.data, status=status.HTTP_200_OK)

class NGORegisterView(generics.GenericAPIView):

    serializer_class = NGORegisterSerializer
    renderer_classes = (UserRenderer,)

    def post(self, request):
        user = request.data
        serializer = self.serializer_class(data=user)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        data = ngo_admindata(data=request.data,context={"request":request})
        data.is_valid(raise_exception=True)
        data.save()
        user_data = serializer.data
        user = User.objects.get(email=user_data['email'])
        token = RefreshToken.for_user(user).access_token
        print(token)        	
        return Response(user_data, status=status.HTTP_201_CREATED)

class NGOLoginAPIView(generics.GenericAPIView):
	serializer_class = NGOLoginSerializer
	def post(self, request):
		serializer = self.serializer_class(data=request.data)
		serializer.is_valid(raise_exception=True)
		return Response(serializer.data, status=status.HTTP_200_OK)
# class LogoutAPIView(generics.GenericAPIView):
#     serializer_class = LogoutSerializer

#     permission_classes = (permissions.IsAuthenticated,)

#     def post(self, request,*args, **kwargs):

#         serializer = self.serializer_class(data=request.data)
#         serializer.is_valid(raise_exception=True)
#         serializer.save()

#         return Response(status=status.HTTP_204_NO_CONTENT)


@api_view(['POST'])
def create(request):
        try:
            serializer=ReviewSerializer(data=request.data,context={"request":request})
            serializer.is_valid(raise_exception=True)
            serializer.save()
            dict_response={"error":False,"message":"Data Saved Successfully"}
        except:
            dict_response={"error":True,"message":"Error During Saving Data"}
        return Response(dict_response)

# class NGODetailsView(APIView):
#     @api_view(['GET','POST'])
#     def NGODetailsView(request):
#         # def get(self,request):
#         if request.method=="GET":
#             d=NGODetails.objects.all()  
#             serializer=NGODetailSerializer(d,many=True)
#             return Response(serializer.data)

#         if request.method=="POST":
#             Detail_serializer=NGODetailSerializer(data=request.data)
#             if Detail_serializer.is_valid():
#                 Detail_serializer.save()
#                 return Response(Detail_serializer.data,status=status.HTTP_201_CREATED)
#             else:
#                 return Response(Detail_serializer.errors,status=status.HTTP_400_BAD_REQUEST)


class NGODetailsView(APIView):
    serializer_class = NGODetailSerializer
    def post(self, request):
        Detail_serializer=NGODetailSerializer(data=request.data)
        if Detail_serializer.is_valid():
            Detail_serializer.save()
            return Response(Detail_serializer.data,status=status.HTTP_201_CREATED)
        else:
            return Response(Detail_serializer.errors,status=status.HTTP_400_BAD_REQUEST)

    def get(self,request):
        d=NGODetails.objects.all()  
        serializer=NGODetailSerializer(d,many=True)
        return Response(serializer.data)

    

class EventDetailsView(APIView):
    serializer_class = EventDetailSerializer
    def post(self, request):
        Detail_serializer=EventDetailSerializer(data=request.data)
        if Detail_serializer.is_valid():
            Detail_serializer.save()
            return Response(Detail_serializer.data,status=status.HTTP_201_CREATED)
        else:
            return Response(Detail_serializer.errors,status=status.HTTP_400_BAD_REQUEST)

    def get(self,request):
        d=EventDetails.objects.all()  
        serializer=EventDetailSerializer(d,many=True)
        return Response(serializer.data)

class CrowdFundingDetailsView(APIView):
    serializer_class = CrowdFundingDetailSerializer
    def post(self, request):
        Detail_serializer=CrowdFundingDetailSerializer(data=request.data)
        if Detail_serializer.is_valid():
            Detail_serializer.save()
            return Response(Detail_serializer.data,status=status.HTTP_201_CREATED)
        else:
            return Response(Detail_serializer.errors,status=status.HTTP_400_BAD_REQUEST)

    def get(self,request):
        d=CrowdFundingDetails.objects.all()  
        serializer=CrowdFundingDetailSerializer(d,many=True)
        return Response(serializer.data)


class FundingView(APIView):
    serializer_class = FundingSerializer
    def post(self,request):
        name=request.POST.get('name')
        amt=request.POST.get('amt')
        data=Funding.objects.create(name=name,amt=amt)
        data.save()
        return Response({'name':name, 'amt':amt})
    # def post(self, request):
    #     Detail_serializer=FundingSerializer(data=request.data)
    #     if Detail_serializer.is_valid():
    #         Detail_serializer.save()
    #         return Response(Detail_serializer.data,status=status.HTTP_201_CREATED)
    #     else:
    #         return Response(Detail_serializer.errors,status=status.HTTP_400_BAD_REQUEST)

    # def get(self,request):
    #     d=CrowdFundingDetails.objects.all()  
    #     serializer=CrowdFundingDetailSerializer(d,many=True)
    #     return Response(serializer.data)
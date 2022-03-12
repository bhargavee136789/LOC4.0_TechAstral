from django.urls import path
from .import views 
from .views import RegisterView,  LoginAPIView,NGORegisterView,NGOLoginAPIView,NGODetailsView,EventDetailsView,CrowdFundingDetailsView
from rest_framework_simplejwt.views import (
    TokenRefreshView,
)
urlpatterns = [
    path('register/', RegisterView.as_view(), name="register"),
    path('login/', LoginAPIView.as_view(), name="login"),
    path('ngo/register/', NGORegisterView.as_view(), name="ngo_register"),
    path('ngo/login/', NGOLoginAPIView.as_view(), name="ngo_login"),
    # path('logout/', LogoutAPIView.as_view(), name="logout"),
    # path('relative/',relativeRegisterView.as_view(),name="relative"),
    # path('relativedata/',relativedata.as_view(),name="relativedata"),
    path('NGODetails',NGODetailsView.as_view(),name='NGODetails'),
    path('EventDetails/',EventDetailsView.as_view(),name='EventDetails'),
    path('CrowdFundingDetails/',CrowdFundingDetailsView.as_view(),name='CrowdundDetails'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('review',views.create,name='review')
]
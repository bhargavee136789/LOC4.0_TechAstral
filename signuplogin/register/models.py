from django.db import models

# Create your models here.
from django.contrib.auth.models import (
    AbstractBaseUser, BaseUserManager, PermissionsMixin)
from rest_framework_simplejwt.tokens import RefreshToken

class UserManager(BaseUserManager):

    def create_user(self, username, email, password=None):
        if username is None:
            raise TypeError('Users should have a username')
        if email is None:
            raise TypeError('Users should have a Email')

        user = self.model(username=username, email=self.normalize_email(email))
        user.set_password(password)
        user.save()
        return user

    def create_superuser(self, username, email, password=None):
        if password is None:
            raise TypeError('Password should not be none')

        user = self.create_user(username, email, password)
        user.is_superuser = True
        user.is_staff = True
        user.save()
        return user
AUTH_PROVIDERS = {'facebook': 'facebook', 'google': 'google',
                   'email': 'email'}
class User(AbstractBaseUser, PermissionsMixin):
    username = models.CharField(max_length=255, unique=True, db_index=True)
    email = models.EmailField(max_length=255, unique=True, db_index=True)
    is_verified = models.BooleanField(default=True)
    is_active = models.BooleanField(default=True)
    is_staff = models.BooleanField(default=False)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    auth_provider = models.CharField(
        max_length=255, blank=False,
        null=False, default=AUTH_PROVIDERS.get('email'))

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['username']

    objects = UserManager()

    def __str__(self):
        return self.email

    def tokens(self):
        refresh = RefreshToken.for_user(self)
        return {
            'refresh': str(refresh),
            'access': str(refresh.access_token)
        }
class admin_reg(models.Model):
    email=models.EmailField(max_length=255, unique=True)
    username= models.CharField(max_length=27, unique=True)
    password=models.TextField(max_length=25,default='')
    phone=models.CharField(max_length=255,default='')
    area=models.CharField(max_length=1255,default='')
    pincode=models.CharField(max_length=255,default='')

    def __Str__(self):
        return self.username


class ngo_admin_reg(models.Model):
    email = models.EmailField(max_length=255,unique=True)
    username = models.CharField(max_length=255,unique=True)
    password = models.CharField(max_length=255)
 
    def __Str__(self):
        return self.username

class Review(models.Model):
    username=models.CharField(max_length=25)
    text=models.TextField(max_length=1000)

class NGODetails(models.Model):
    username=models.CharField(max_length=25)
    owner=models.TextField(max_length=1000)
    location=models.TextField(max_length=1000)
    moto=models.TextField(max_length=1000)
    desc=models.TextField(max_length=1000)
    achievement=models.TextField(max_length=1000)

class Funding(models.Model):
    name=models.CharField(max_length=25)
    amt=models.CharField(max_length=23)
# EVENT_TYPES=[{'volunteer','volunteer'},{'funding','funding'}]

# class EventDetails(models.Model):
#     name=models.CharField(max_length=25)
#     date=models.DateField()
#     location=models.TextField(max_length=1000)
#     type1=models.CharField(max_length=1000,choices=EVENT_TYPES)
#     desc=models.TextField(max_length=1000)

# class VolunteerEventDetails(models.Model):
#     mainevent=models.ForeignKey(EventDetails)
#     time=models.CharField(max_length=10)
#     reqdppl=models.IntegerField()
#     deadline=models.CharField(max_length=225,default='')

# class CrowdFundingDetails(models.Model):
#     mainevent=models.ForeignKey(EventDetails)
#     starttime=models.CharField(max_length=10)
#     endtime=models.CharField(max_length=10)
#     reqdamt=models.IntegerField()

# class volunteer(models.Model):
#     event=models.ForeignKey(EventDetails)
#     username=models.CharField()
class EventDetails(models.Model):
    name=models.CharField(max_length=25)
    date=models.DateField(max_length=10)
    time=models.CharField(max_length=10)
    location=models.TextField(max_length=1000)
    type1=models.TextField(max_length=1000)
    desc=models.TextField(max_length=1000)
    reqdppl=models.CharField(max_length=255,default='')
    deadline=models.CharField(max_length=225,default='')

class CrowdFundingDetails(models.Model):
    name=models.CharField(max_length=25)
    date=models.DateField(max_length=10)
    starttime=models.CharField(max_length=10)
    location=models.TextField(max_length=1000)
    type1=models.TextField(max_length=1000)
    desc=models.TextField(max_length=1000)
    endtime=models.CharField(max_length=10)
    reqdamt=models.CharField(max_length=10)
import pickle
import pygame
from random import randint
import time
import utils

from Conroller.Controller import Controller
from Model.Drone import Drone
from Model.Map import Map

class GUI:
    def __init__(self, drone, dmap):
        self.__controller = Controller(drone, dmap)

    def start(self):
        self.__controller.start()
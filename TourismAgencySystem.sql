PGDMP  	        
            |            tourismagencysystem    15.7    16.3 /    /           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            0           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            1           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            2           1262    17006    tourismagencysystem    DATABASE     �   CREATE DATABASE tourismagencysystem WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
 #   DROP DATABASE tourismagencysystem;
                postgres    false            S           1247    17025    hotel_star_enum    TYPE     n   CREATE TYPE public.hotel_star_enum AS ENUM (
    '1',
    '2',
    '3',
    '4',
    '5',
    '6',
    '7'
);
 "   DROP TYPE public.hotel_star_enum;
       public          postgres    false            �            1259    17073    hostel_type    TABLE     �   CREATE TABLE public.hostel_type (
    hostel_type_id integer NOT NULL,
    hotel_id integer NOT NULL,
    hostel_type character varying(200) NOT NULL
);
    DROP TABLE public.hostel_type;
       public         heap    postgres    false            �            1259    17072    hostel_type_hostel_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hostel_type_hostel_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.hostel_type_hostel_type_id_seq;
       public          postgres    false    219            3           0    0    hostel_type_hostel_type_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.hostel_type_hostel_type_id_seq OWNED BY public.hostel_type.hostel_type_id;
          public          postgres    false    218            �            1259    17040    hotels    TABLE     �  CREATE TABLE public.hotels (
    hotel_id integer NOT NULL,
    hotel_name character varying(300) NOT NULL,
    hotel_city character varying(300) NOT NULL,
    hotel_district character varying(300) NOT NULL,
    hotel_address character varying(300) NOT NULL,
    hotel_email character varying(300) NOT NULL,
    hotel_phone_number character varying(300) NOT NULL,
    hotel_star character varying(20) NOT NULL,
    hotel_facility character varying(300) NOT NULL
);
    DROP TABLE public.hotels;
       public         heap    postgres    false            �            1259    17039    hotels_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotels_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.hotels_hotel_id_seq;
       public          postgres    false    217            4           0    0    hotels_hotel_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.hotels_hotel_id_seq OWNED BY public.hotels.hotel_id;
          public          postgres    false    216            �            1259    17115    reservation    TABLE       CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    room_id integer NOT NULL,
    customer_name character varying(250) NOT NULL,
    customer_identity_number character varying(250) NOT NULL,
    customer_phone_number character varying(250) NOT NULL,
    customer_email character varying(250) NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    adult_number integer NOT NULL,
    child_number integer NOT NULL,
    customer_note character varying(250),
    total_price integer NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17114    reservation_reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.reservation_reservation_id_seq;
       public          postgres    false    223            5           0    0    reservation_reservation_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.reservation_reservation_id_seq OWNED BY public.reservation.reservation_id;
          public          postgres    false    222            �            1259    17124    room    TABLE     Z  CREATE TABLE public.room (
    room_id integer NOT NULL,
    hotel_id integer NOT NULL,
    season_id integer NOT NULL,
    hostel_type_id integer NOT NULL,
    room_type character varying(250) NOT NULL,
    bed_number integer NOT NULL,
    room_area integer NOT NULL,
    room_number integer NOT NULL,
    adult_price integer NOT NULL,
    child_price integer NOT NULL,
    tv character varying(300) NOT NULL,
    minibar character varying(300) NOT NULL,
    game_console character varying(300) NOT NULL,
    safe_box character varying(300) NOT NULL,
    projection character varying(300) NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17123    room_room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.room_room_id_seq;
       public          postgres    false    225            6           0    0    room_room_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.room_room_id_seq OWNED BY public.room.room_id;
          public          postgres    false    224            �            1259    17108    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    hotel_id integer NOT NULL,
    season_start_date date NOT NULL,
    season_end_date date NOT NULL,
    season_name character varying(50) NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17107    season_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.season_season_id_seq;
       public          postgres    false    221            7           0    0    season_season_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.season_season_id_seq OWNED BY public.season.season_id;
          public          postgres    false    220            �            1259    17008    users    TABLE     �   CREATE TABLE public.users (
    user_id integer NOT NULL,
    user_name character varying(200) NOT NULL,
    user_password character varying(15) NOT NULL,
    user_type character varying(5) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    17007    users_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.users_user_id_seq;
       public          postgres    false    215            8           0    0    users_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;
          public          postgres    false    214            �           2604    17076    hostel_type hostel_type_id    DEFAULT     �   ALTER TABLE ONLY public.hostel_type ALTER COLUMN hostel_type_id SET DEFAULT nextval('public.hostel_type_hostel_type_id_seq'::regclass);
 I   ALTER TABLE public.hostel_type ALTER COLUMN hostel_type_id DROP DEFAULT;
       public          postgres    false    218    219    219            �           2604    17043    hotels hotel_id    DEFAULT     r   ALTER TABLE ONLY public.hotels ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotels_hotel_id_seq'::regclass);
 >   ALTER TABLE public.hotels ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    17118    reservation reservation_id    DEFAULT     �   ALTER TABLE ONLY public.reservation ALTER COLUMN reservation_id SET DEFAULT nextval('public.reservation_reservation_id_seq'::regclass);
 I   ALTER TABLE public.reservation ALTER COLUMN reservation_id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    17127    room room_id    DEFAULT     l   ALTER TABLE ONLY public.room ALTER COLUMN room_id SET DEFAULT nextval('public.room_room_id_seq'::regclass);
 ;   ALTER TABLE public.room ALTER COLUMN room_id DROP DEFAULT;
       public          postgres    false    225    224    225            �           2604    17111    season season_id    DEFAULT     t   ALTER TABLE ONLY public.season ALTER COLUMN season_id SET DEFAULT nextval('public.season_season_id_seq'::regclass);
 ?   ALTER TABLE public.season ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    220    221    221            �           2604    17011    users user_id    DEFAULT     n   ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);
 <   ALTER TABLE public.users ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    215    214    215            &          0    17073    hostel_type 
   TABLE DATA           L   COPY public.hostel_type (hostel_type_id, hotel_id, hostel_type) FROM stdin;
    public          postgres    false    219   �9       $          0    17040    hotels 
   TABLE DATA           �   COPY public.hotels (hotel_id, hotel_name, hotel_city, hotel_district, hotel_address, hotel_email, hotel_phone_number, hotel_star, hotel_facility) FROM stdin;
    public          postgres    false    217   >:       *          0    17115    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, customer_name, customer_identity_number, customer_phone_number, customer_email, check_in_date, check_out_date, adult_number, child_number, customer_note, total_price) FROM stdin;
    public          postgres    false    223   N<       ,          0    17124    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_id, season_id, hostel_type_id, room_type, bed_number, room_area, room_number, adult_price, child_price, tv, minibar, game_console, safe_box, projection) FROM stdin;
    public          postgres    false    225   #=       (          0    17108    season 
   TABLE DATA           f   COPY public.season (season_id, hotel_id, season_start_date, season_end_date, season_name) FROM stdin;
    public          postgres    false    221   +>       "          0    17008    users 
   TABLE DATA           M   COPY public.users (user_id, user_name, user_password, user_type) FROM stdin;
    public          postgres    false    215   �>       9           0    0    hostel_type_hostel_type_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.hostel_type_hostel_type_id_seq', 40, true);
          public          postgres    false    218            :           0    0    hotels_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotels_hotel_id_seq', 8, true);
          public          postgres    false    216            ;           0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 42, true);
          public          postgres    false    222            <           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 31, true);
          public          postgres    false    224            =           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 30, true);
          public          postgres    false    220            >           0    0    users_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.users_user_id_seq', 8, true);
          public          postgres    false    214            �           2606    17078    hostel_type hostel_type_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.hostel_type
    ADD CONSTRAINT hostel_type_pkey PRIMARY KEY (hostel_type_id);
 F   ALTER TABLE ONLY public.hostel_type DROP CONSTRAINT hostel_type_pkey;
       public            postgres    false    219            �           2606    17047    hotels hotels_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (hotel_id);
 <   ALTER TABLE ONLY public.hotels DROP CONSTRAINT hotels_pkey;
       public            postgres    false    217            �           2606    17122    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    223            �           2606    17131    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    225            �           2606    17113    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    221            �           2606    17013    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    215            &   �   x�m��
�0��٧�H����(zo^B�j16���o�\*�z�fwfv�d�mG�x�u0$x�]3q
���νH�,c'7�W��ڍ}��$fR	����W��[���T-jS�)����{Rp�Y��ݤ2fRΊ��g��Ϸi2m��5��7O������W�� �7�L\S      $      x�mRKn�@]ۧ�UVVh~K 	������ͦ0Eh��j�Q�r��Xf;lr�{Mـ㉐���.׫z���(����W��5��w"���`�p��5��(T�&+k�1�sr�l���0�X(f	���vbI���D �P��OJ����xT9��yE�$3�B��g&R��?�����ӁN�y#���)�LQY�Ep�se�l�_����Ν��U0AU�f�uh�Z�V�d�`FF�p#E��pC�.����"���F���M�w�d���w#`H9\�l0ᒍ�1\?�o@S����Ĝ�S��(�ۅn�y$����sW������z_8��� �ni��ղ���o���~cr�6�Z��yV{��x�B�+j�o˝[��s.�?�[c|�;�I5�I�<<+��y5S$�
.��B�Ƣ"���?��G�����8�ū�3	�xT�V]�7S��r�t���k>��.ilL�2nB��"M���]���5B���/���ur��V��J6�9_�a�p�Xq      *   �   x���A
�0E׿��,��I[wn\z �M�E����O�i����.��?f�~�(���G�A���'*)ǌ{����}��:�0�U7E0�.�Y8��� @�6R��u�pI�d+`.9ǌ�6~s��%���&a7����D�٦^��3���2٨p��m�ގ!�~�Ƥj�ᵭ��UK�1#��V��:N��糭b�vUQ/��R�      ,   �   x�u�Mk!�s�1E�1z﹗=z*e�t����q�q���C�7*��^o���3)~�=�o�����&�|��	��Tb����*4%T<��E-�ݕQ�6�L����v@���L�4�V0txtʽӽV]�j	4*^�׿���eA�Ѭd4�����	� �<bMBFz����0
P��v>�̐�e ��XV�C3�X�E�f���L�W����bL��*�:����-����c.3/ι0��d      (   �   x���1�0��+����l��
��)R�%�c��"d�\�����JL⤳��Sbg=��z���  K��<o ��:��(Rl�G0�E;Vl�;�"l�֎6O�-d��	
[�4R����U� ��oޡ����cV�-fZ      "   Q   x�3�LL����4426�tt����2�,.ILK��8��q�pV&'%'�q�[XBՙq���@���c�i
����� L�     
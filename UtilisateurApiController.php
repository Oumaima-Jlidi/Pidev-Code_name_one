<?php

namespace App\Controller;

use App\Entity\User;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Doctrine\ORM\Mapping\DiscriminatorMap;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;


class UtilisateurApiController extends AbstractController
{
    public function __construct(private UserPasswordHasherInterface $passwordHasher)
    {
    }

    #[Route('/user/signup', name: 'app_api_register')]
    public function signupAction (Request $request , UserPasswordEncoderInterface $passwordEncoder)
    {
       $email=$request->query->get(key:"email");
       $password=$request->query->get(key:"password");

       if(!filter_var($email,filter:FILTER_VALIDATE_EMAIL)){
        return new Response(content:"email invalide.");
       }

       $user = new User();
       $roles[]='ROLE_PATIENT';
       $user->setEmail( $email);
       $user->setRoles($roles);
       $user->setPassword($this->passwordHasher->hashPassword($user, $password));
       $user->setNom("");
       $user->setPrenom("");
       $user->setCin(0);
       $user->setSexe("");
       $user->setTelephone(0);
       $user->setGouvernorat("");
       $user->setAdresse("");
       $user->setConfirmPassword($this->passwordHasher->hashPassword($user, $password));

    

       try {
        $em=$this->getDoctrine()->getManager();
        $em->persist($user);
        $em->flush();
        return new JsonResponse(data:"Account is created",status:200);//200 : Http result of server = ok

       }catch(\Exception $ex){
        return new Response(content:"exception".$ex->getMessage());



       }


    }

//Mobile
#[Route('/users/All', name: 'app_admins_liste')]
public function ListeAdmin(UserRepository $userRepository, SerializerInterface $serializer)
{
    $users = $userRepository->findAll();
    $formatted = $serializer->normalize($users, null, [
        'attributes' => [
            'id',
            'email',
            'roles',
            'password',
            'nom',
            'prenom',
            'cin',
            'sexe',
            'telephone',
            'gouvernorat',
            'adresse',
            'image'
        ]
    ]);

    $json = json_encode($formatted);
    return new Response($json);
}





    
    #[Route('/users/loginAction', name: 'app_api_login')]
    public function signinAction(Request $request)
    {
        $email = $request->query->get('email');
        $password = $request->query->get('password');
    
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(User::class)->findOneBy(['email' => $email]);
    
        if ($user) {
            if (password_verify($password, $user->getPassword())) {
                $serializer = new Serializer([new ObjectNormalizer()]);
                $formatted = $serializer->normalize($user, null, [
                    'attributes' => [
                        'id',
                        'email',
                        'roles',
                        'password',
                        'nom',
                        'prenom',
                        'cin',
                        'sexe',
                        'telephone',
                        'gouvernorat',
                        'adresse',
                        'image'
                    ]
                ]);
                return new JsonResponse($formatted);
            } else {
                return new Response('Password not found');
            }
        } else {
            return new Response('User not found');
        }
    }
    

    #[Route('/users/updateUser', name: 'app_gestion_profile')]

    public function editUser(Request $request){
            $id=$request->get(key:"id");
            $nom=$request->query->get(key:"nom");
            $prenom=$request->query->get(key:"prenom");
            $tel=$request->query->get(key:"tel");
            $adresse=$request->query->get(key:"address");
            $cin=$request->query->get(key:"cin");
            $sexe=$request->query->get(key:"sexe");
            $gouvernorat=$request->query->get(key:"gouvernorat");
            $em=$this->getDoctrine()->getManager();
            
            
            $user=$em->getRepository(User::class)->find($id);
            $user->setNom( $nom);
            $user->setPrenom( $prenom);
            $user->setCin($cin);
            $user->setSexe($sexe);
            $user->setTelephone( $tel);
            $user->setGouvernorat($gouvernorat);
            $user->setAdresse($adresse);



           
            try {
                $em=$this->getDoctrine()->getManager();
                $em->persist($user);
                $em->flush();
                return new JsonResponse(data:"Success",status:200);//200 : Http result of server = ok
        
               }catch(\Exception $ex){

                return new Response(content:"failed".$ex->getMessage());
        
        
        
               }
          
    }

    #[Route('/users/updateImg', name: 'updateimg', methods: ['POST','GET'])]
    public function updateimg(Request $request)
    {
        $user = new User();
        $id = $request->query->get("id");
        $img = $request->query->get("Img");   
        $rep = $this->getDoctrine()->getManager();    
        $user = $rep->getRepository(User::class)->find($id);
        $user->setImage($img);
        $rep->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("Image ajouter");
        return new JsonResponse($formatted);
        
    }

      #[Route('/users/updatepassword', name: 'updatepassword')]
      public function updatepassword(Request $request,UserPasswordEncoderInterface $passwordEncoder) :JsonResponse
      {
        $user = new User();
        $email = $request->query->get("email");
        $password = $request->query->get("password");


        $rep = $this->getDoctrine()->getManager();
       
        $user = $this->getDoctrine()->getRepository(User::class)->findOneBy(['email' => $email]);

        $user->setPassword($this->passwordHasher->hashPassword($user, $password));



     
         
        $rep->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("Mot de passe a ete changer");
        return new JsonResponse($formatted);
          
        }

      #[Route('/users/checkemail', name: 'checkemail')]
      public function checkemail(Request $request):JsonResponse
      {
        $user = new User();
        $email = $request->query->get("email");
   
 $rep = $this->getDoctrine()->getManager();
   
     
          $user = $this->getDoctrine()->getRepository(User::class)->findOneBy(['email' => $email]);

    
if($user){

    $serializer = new Serializer([new ObjectNormalizer()]);
    $formatted = $serializer->normalize("email exist");
    return new JsonResponse($formatted);

}

      

          
        }

#[Route('/user/deletedisUser', name: 'deleteUser', methods: ['DELETE'])]
public function deleteUser(Request $request): Response
{
    $user = new User();
    $id = $request->query->get("id");
    $rep = $this->getDoctrine()->getRepository(User::class);
    $em = $this->getDoctrine()->getManager();
    $user = $rep->find($id);
    $em->remove($user);
    $em->flush();
    $serializer = new Serializer([new ObjectNormalizer()]);
$formatted = $serializer->normalize("user got deleted");
return new JsonResponse($formatted);
}
}


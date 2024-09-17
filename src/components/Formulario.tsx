"use client";

import React, { Dispatch, SetStateAction, useState } from 'react';
import { useForm } from 'react-hook-form';
import { Form, FormControl, FormField, FormItem, FormLabel } from '@/components/ui/form';
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from './ui/card';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { Comodo } from './ComodoList';

const formSchema = z.object({
  name: z.string().max(50, { 
  }),

});



type FormValues = z.infer<typeof formSchema>;

const handleSubimitLogin = (data: FormValues)=>{
  console.log(data)
}
const Formulario = ({onSubmit, setState}:{onSubmit: (Comodo: Comodo) => void, setState: Dispatch<SetStateAction<boolean>>}) => {
  const [isEditMode, setIsEditMode] = useState(false); 
  const form = useForm<FormValues>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      name: ''
    },
  });

  return (
    <div className='fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center'>
      <Card className='w-full max-w-md relative'>
        {/* Botão de Fechar */}
        <button
          onClick={() => setState(false)}
          className='absolute top-2 right-2 text-white text-2xl font-bold'
        >
          &times;
        </button>

        <CardHeader className='bg-[#FFC314]'>
          <CardTitle>{isEditMode ? "Editar comodo" : "Adicionar Novo comodo"}</CardTitle>
        </CardHeader>

        <CardContent>
          <Form {...form}>
            <form className="space-y-4" onSubmit={form.handleSubmit(handleSubimitLogin)}>
              <FormField 
                control={form.control}
                name="name"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Nome do comodo</FormLabel>
                    <FormControl>
                      <input placeholder="Digite o nome" {...field} />
                    </FormControl>
                  </FormItem>
                )}
              />


              
              <div className="flex space-x-2">
                <button className="w-full bg-[#FFC314] text-center flex items-center justify-center p-2 rounded" type="submit">
                  {isEditMode ? "Salvar Alterações" : "Adicionar"}
                </button>
                <button
                  type="button"
                  onClick={() => setState(false)}
                  className="w-full bg-gray-500 text-center flex items-center justify-center p-2 rounded text-white"
                >
                  Cancelar
                </button>
              </div>
            </form>
          </Form>
        </CardContent>
      
      </Card>
    </div>
  );
};

export default Formulario;

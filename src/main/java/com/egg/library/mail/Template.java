package com.egg.library.mail;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.LoanVO;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class Template {

    public String getTemplateMail(LoanVO loanVO){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"+
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">"+
                "<head>"+
                "    <meta charset=\"UTF-8\">"+
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">"+
                "    <meta name=\"x-apple-disable-message-reformatting\">"+
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
                "    <meta content=\"telephone=no\" name=\"format-detection\">"+
                "    <title>Nuevo mensaje 3</title>"+
                "    <!--[if (mso 16)]>"+
                "    <style type=\"text/css\">"+
                "a {text-decoration: none;}"+
                "</style>"+
                "    <![endif]-->"+
                "    <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->"+
                "    <!--[if gte mso 9]>"+
                "    <xml>"+
                "        <o:OfficeDocumentSettings>"+
                "            <o:AllowPNG></o:AllowPNG>"+
                "            <o:PixelsPerInch>96</o:PixelsPerInch>"+
                "        </o:OfficeDocumentSettings>"+
                "    </xml>"+
                "    <![endif]-->"+
                "    <!--[if !mso]><!-- -->"+
                "    <link href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i\" rel=\"stylesheet\">"+
                "    <link href=\"https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i\" rel=\"stylesheet\">"+
                "    <!--<![endif]-->"+
                "    <style type=\"text/css\">"+
                "#outlook a {"+
                "padding:0;"+
                "}"+
                ".es-button {"+
                "mso-style-priority:100!important;"+
                "text-decoration:none!important;"+
                "}"+
                "a[x-apple-data-detectors] {"+
                "color:inherit!important;"+
                "text-decoration:none!important;"+
                "font-size:inherit!important;"+
                "font-family:inherit!important;"+
                "font-weight:inherit!important;"+
                "line-height:inherit!important;"+
                "}"+
                ".es-desk-hidden {"+
                "display:none;"+
                "float:left;"+
                "overflow:hidden;"+
                "width:0;"+
                "max-height:0;"+
                "line-height:0;"+
                "mso-hide:all;"+
                "}"+
                "[data-ogsb] .es-button {"+
                "border-width:0!important;"+
                "padding:10px 20px 10px 20px!important;"+
                "}"+
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-menu td a { font-size:16px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:20px!important; display:block!important; border-left-width:0px!important; border-right-width:0px!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } }"+
                "</style>"+
                "</head>"+
                "<body style=\"width:100%;font-family:arial,helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">"+
                "<div class=\"es-wrapper-color\" style=\"background-color:#F6F6F6\">"+
                "    <!--[if gte mso 9]>"+
                "    <v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">"+
                "        <v:fill type=\"tile\" color=\"#f6f6f6\"></v:fill>"+
                "    </v:background>"+
                "    <![endif]-->"+
                "    <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top\">"+
                "        <tr>"+
                "            <td valign=\"top\" style=\"padding:0;Margin:0\">"+
                "                <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#EFEFEF;background-repeat:repeat;background-position:center top\">"+
                "                    <tr>"+
                "                        <td align=\"center\" bgcolor=\"#efefef\" style=\"padding:0;Margin:0;background-color:#efefef\">"+
                "                            <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">"+
                "                                <tr>"+
                "                                    <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">"+
                "                                        <!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:180px\" valign=\"top\"><![endif]-->"+
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">"+
                "                                            <tr>"+
                "                                                <td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:180px\">"+
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_62e92c60c036a1c993a7dca50c996692/images/logo.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"30\"></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table>"+
                "                                        <!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:360px\" valign=\"top\"><![endif]-->"+
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                            <tr>"+
                "                                                <td align=\"left\" style=\"padding:0;Margin:0;width:360px\">"+
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"left\" style=\"padding:20px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato,helvetica, arial, sans-serif;line-height:27px;color:#333333;font-size:18px\"><strong>Booking</strong></p></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table>"+
                "                                        <!--[if mso]></td></tr></table><![endif]--></td>"+
                "                                </tr>"+
                "                                <tr>"+
                "                                    <td style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px;background-color:transparent;background-position:left bottom\" bgcolor=\"transparent\" align=\"left\">"+
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                            <tr>"+
                "                                                <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">"+
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://rywkbv.stripocdn.email/content/guids/CABINET_62e92c60c036a1c993a7dca50c996692/images/learningconcept_Iem.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"560\"></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table></td>"+
                "                                </tr>"+
                "                            </table></td>"+
                "                    </tr>"+
                "                </table>"+
                "                <table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#EFEFEF;background-repeat:repeat;background-position:center top\">"+
                "                    <tr>"+
                "                        <td align=\"center\" style=\"padding:0;Margin:0\">"+
                "                            <table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">"+
                "                                <tr>"+
                "                                    <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">"+
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                            <tr>"+
                "                                                <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">"+
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"center\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto,helvetica, arial, sans-serif;line-height:39px;color:#333333;font-size:26px\">¡Se ha registrado un nuevo préstamo!<br><span style=\"font-size:22px\">Código :<strong> </strong><span style=\"color:#FFA500\"><strong>"+loanVO.getId()+"</strong></span></span></p></td>"+
                "                                                        </tr>"+
                "                                                        <tr>"+
                "                                                            <td align=\"center\" style=\"padding:20px;Margin:0;font-size:0\">"+
                "                                                                <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                                    <tr>"+
                "                                                                        <td style=\"padding:0;Margin:0;border-bottom:1px solid #cccccc;background:none;height:1px;width:100%;margin:0px\"></td>"+
                "                                                                    </tr>"+
                "                                                                </table></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table></td>"+
                "                                </tr>"+
                "                                <tr>"+
                "                                    <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">"+
                "                                        <!--[if mso]><table style=\"width:560px\" cellpadding=\"0\""+
                "                                                            cellspacing=\"0\"><tr><td style=\"width:180px\" valign=\"top\"><![endif]-->"+
                "                                        <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">"+
                "                                            <tr>"+
                "                                                <td  class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:180px\">"+
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr style=\"padding:0;Margin:0;\"><td align=\"center\" style=\"padding:0;Margin:0;\"><img class=\"adapt-img\" src=\"https://i.ibb.co/MDJ7GYf/book.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"55\"></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table>"+
                "                                        <!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:360px\" valign=\"top\"><![endif]-->"+
                "                                        <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                            <tr>"+
                "                                                <td align=\"left\" style=\"padding:0;Margin:0;width:360px\">"+
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, helvetica, arial, sans-serif;line-height:27px;color:#333333;font-size:18px\"><span style=\"color:#000000\"><strong>"+loanVO.getBook().getTitle()+"</strong></span><br><span style=\"font-size:14px\"><span style=\"color:#000000\">"+loanVO.getBook().getAuthor().getName()+"</span><br><span style=\"color:#a9a9a9;font-size:12px\">"+loanVO.getBook().getEditorial().getName()+"<br><strong>Fecha de vencimiento "+loanVO.getReturnDate().format(dateTimeFormatter)+"</strong></span></span></p></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table>"+
                "                                        <!--[if mso]></td></tr></table><![endif]--></td>"+
                "                                </tr>"+
                "                            </table></td>"+
                "                    </tr>"+
                "                </table>"+
                "                <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">"+
                "                    <tr>"+
                "                        <td align=\"center\" bgcolor=\"#efefef\" style=\"padding:0;Margin:0;background-color:#efefef\">"+
                "                            <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px\">"+
                "                                <tr>"+
                "                                    <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">"+
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                            <tr>"+
                "                                                <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">"+
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"center\" style=\"padding:20px;Margin:0;font-size:0\">"+
                "                                                                <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                                    <tr>"+
                "                                                                        <td style=\"padding:0;Margin:0;border-bottom:1px solid #cccccc;background:none;height:1px;width:100%;margin:0px\"></td>"+
                "                                                                    </tr>"+
                "                                                                </table></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table></td>"+
                "                                </tr>"+
                "                                <tr>"+
                "                                    <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">"+
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                            <tr>"+
                "                                                <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">"+
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"center\" style=\"padding:20px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato,helvetica, arial, sans-serif;line-height:28px;color:#333333;font-size:14px\">Esperamos que los disfrutes, no olvides acercarte a la biblioteca antes de la fecha de vencimiento para devolver el libro o renovar el préstamo.</p></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table></td>"+
                "                                </tr>"+
                "                            </table></td>"+
                "                    </tr>"+
                "                </table>"+
                "                <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">"+
                "                    <tr>"+
                "                        <td align=\"center\" bgcolor=\"#efefef\" style=\"padding:0;Margin:0;background-color:#efefef\">"+
                "                            <table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#efefef\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#efefef;width:600px\">"+
                "                                <tr>"+
                "                                    <td align=\"left\" bgcolor=\"#cccccc\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#cccccc\">"+
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                            <tr>"+
                "                                                <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">"+
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">"+
                "                                                                <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                                    <tr>"+
                "                                                                        <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:10px\"><img title=\"Facebook\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/facebook-circle-colored.png\" alt=\"Fb\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>"+
                "                                                                        <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:10px\"><img title=\"Twitter\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/twitter-circle-colored.png\" alt=\"Tw\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>"+
                "                                                                        <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><img title=\"Instagram\" src=\"https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/instagram-circle-colored.png\" alt=\"Inst\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td>"+
                "                                                                    </tr>"+
                "                                                                </table></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table></td>"+
                "                                </tr>"+
                "                                <tr>"+
                "                                    <td align=\"left\" bgcolor=\"#cccccc\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#cccccc\">"+
                "                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                            <tr>"+
                "                                                <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">"+
                "                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"+
                "                                                        <tr>"+
                "                                                            <td align=\"center\" style=\"padding:5px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato,helvetica, arial, sans-serif;line-height:21px;color:#333333;font-size:14px\">Av. del Libertador 5420, CABA, Argentina</p></td>"+
                "                                                        </tr>"+
                "                                                    </table></td>"+
                "                                            </tr>"+
                "                                        </table></td>"+
                "                                </tr>"+
                "                            </table></td>"+
                "                    </tr>"+
                "                </table></td>"+
                "        </tr>"+
                "    </table>"+
                "</div>"+
                "</body>"+
                "</html>";
    }
    
    public String getWelcomeTemplate(CustomerVO customerVO){
        return  "   <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>  "  +
                "   <html xmlns='http://www.w3.org/1999/xhtml' xmlns:o='urn:schemas-microsoft-com:office:office' style='font-family:arial,helvetica, sans-serif'>  "  +
                "   <head>  "  +
                "   <meta charset='UTF-8'>  "  +
                "   <meta content='width=device-width, initial-scale=1' name='viewport'>  "  +
                "   <meta name='x-apple-disable-message-reformatting'>  "  +
                "   <meta http-equiv='X-UA-Compatible' content='IE=edge'>  "  +
                "   <meta content='telephone=no' name='format-detection'>  "  +
                "   <title>Nuevo mensaje 4</title>  "  +
                "   <!--[if (mso 16)]>  "  +
                "   <style type='text/css'>  "  +
                "   a {text-decoration: none;}  "  +
                "   </style>  "  +
                "   <![endif]-->  "  +
                "   <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->  "  +
                "   <!--[if gte mso 9]>  "  +
                "   <xml>  "  +
                "   <o:OfficeDocumentSettings>  "  +
                "   <o:AllowPNG></o:AllowPNG>  "  +
                "   <o:PixelsPerInch>96</o:PixelsPerInch>  "  +
                "   </o:OfficeDocumentSettings>  "  +
                "   </xml>  "  +
                "   <![endif]-->  "  +
                "   <!--[if !mso]><!-- -->  "  +
                "   <link href='https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i' rel='stylesheet'>  "  +
                "   <link href='https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i' rel='stylesheet'>  "  +
                "   <!--<![endif]-->  "  +
                "   <style type='text/css'>  "  +
                "   #outlook a {  "  +
                "   padding:0;  "  +
                "   }  "  +
                "   .es-button {  "  +
                "   mso-style-priority:100!important;  "  +
                "   text-decoration:none!important;  "  +
                "   }  "  +
                "   a[x-apple-data-detectors] {  "  +
                "   color:inherit!important;  "  +
                "   text-decoration:none!important;  "  +
                "   font-size:inherit!important;  "  +
                "   font-family:inherit!important;  "  +
                "   font-weight:inherit!important;  "  +
                "   line-height:inherit!important;  "  +
                "   }  "  +
                "   .es-desk-hidden {  "  +
                "   display:none;  "  +
                "   float:left;  "  +
                "   overflow:hidden;  "  +
                "   width:0;  "  +
                "   max-height:0;  "  +
                "   line-height:0;  "  +
                "   mso-hide:all;  "  +
                "   }  "  +
                "   [data-ogsb] .es-button {  "  +
                "   border-width:0!important;  "  +
                "   padding:10px 30px 10px 30px!important;  "  +
                "   }  "  +
                "   [data-ogsb] .es-button.es-button-1 {  "  +
                "   padding:10px 30px!important;  "  +
                "   }  "  +
                "   @media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120%!important } h1 { font-size:36px!important; text-align:left } h2 { font-size:26px!important; text-align:left } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:12px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class='gmail-fix'] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:20px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } }  "  +
                "   </style>  "  +
                "   </head>  "  +
                "   <body style='width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0'>  "  +
                "   <div class='es-wrapper-color' style='background-color:#FAFAFA'>  "  +
                "   <!--[if gte mso 9]>  "  +
                "   <v:background xmlns:v='urn:schemas-microsoft-com:vml' fill='t'>  "  +
                "   <v:fill type='tile' color='#fafafa'></v:fill>  "  +
                "   </v:background>  "  +
                "   <![endif]-->  "  +
                "   <table class='es-wrapper' width='100%' cellspacing='0' cellpadding='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#FAFAFA'>  "  +
                "   <tr>  "  +
                "   <td valign='top' style='padding:0;Margin:0'>  "  +
                "   <table cellpadding='0' cellspacing='0' class='es-content' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%'>  "  +
                "   <tr>  "  +
                "   <td class='es-info-area' align='center' style='padding:0;Margin:0'>  "  +
                "   <table class='es-content-body' align='center' cellpadding='0' cellspacing='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px' bgcolor='#FFFFFF'>  "  +
                "   <tr>  "  +
                "   <td align='left' style='padding:20px;Margin:0'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' class='es-infoblock' style='padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, \'helvetica neue\', helvetica, sans-serif;line-height:14px;color:#CCCCCC;font-size:12px'><a target='_blank' href='' style='-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#CCCCCC;font-size:12px'>View online version</a></p></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table>  "  +
                "   <table cellpadding='0' cellspacing='0' class='es-content' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%'>  "  +
                "   <tr>  "  +
                "   <td align='center' style='padding:0;Margin:0'>  "  +
                "   <table bgcolor='#ffffff' class='es-content-body' align='center' cellpadding='0' cellspacing='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px'>  "  +
                "   <tr>  "  +
                "   <td align='left' bgcolor='#e8eff9' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#e8eff9'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' class='es-m-p0r es-m-p0l' style='Margin:0;padding-top:10px;padding-bottom:10px;padding-left:40px;padding-right:40px'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:51px;color:#00ccff;font-size:34px'><strong>Bienvid@ "+customerVO.getName()+"!&nbsp;</strong></p></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table>  "  +
                "   <table cellpadding='0' cellspacing='0' class='es-content' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%'>  "  +
                "   <tr>  "  +
                "   <td align='center' style='padding:0;Margin:0'>  "  +
                "   <table bgcolor='#ffffff' class='es-content-body' align='center' cellpadding='0' cellspacing='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px'>  "  +
                "   <tr>  "  +
                "   <td align='left' bgcolor='#e8eff9' style='Margin:0;padding-top:15px;padding-left:20px;padding-right:20px;padding-bottom:30px;background-color:#e8eff9'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' style='padding:0;Margin:0;padding-top:10px;padding-bottom:10px;font-size:0px'><img src='https://rywkbv.stripocdn.email/content/guids/CABINET_9ca3362f6e3ce2c3ea60da8fe4f8a850/images/78491618321704551.png' alt style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic' width='150'></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   <tr>  "  +
                "   <td align='left' style='Margin:0;padding-bottom:10px;padding-top:20px;padding-left:20px;padding-right:20px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' role='presentation'' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' style='padding:0;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto,helvetica, arial, sans-serif;line-height:30px;color:#333333;font-size:20px'><br>Ya puedes loguearte en el sitio con tus credenciales y comenzar a explorar todo el material que tenemos disponible para vos!</p></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   <tr>  "  +
                "   <td align='left' style='Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:25px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' style='padding:0;Margin:0;padding-top:10px;padding-bottom:10px'><span class='es-button-border' style='border-style:solid;border-color:#2CB543;background:#5C68E2;border-width:0px;display:inline-block;border-radius:6px;width:auto'><a href=\'localhost:8090/login\' class='es-button es-button-1' target='_blank' style='mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:20px;border-style:solid;border-color:#5C68E2;border-width:10px 30px;display:inline-block;background:#5C68E2;border-radius:6px;font-family:arial,helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:24px;width:auto;text-align:center'>COMENZAR</a></span></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table>  "  +
                "   <table cellpadding='0' cellspacing='0' class='es-content' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%'>  "  +
                "   <tr>  "  +
                "   <td class='es-info-area' align='center' style='padding:0;Margin:0'>  "  +
                "   <table class='es-content-body' align='center' cellpadding='0' cellspacing='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px' bgcolor='#FFFFFF'>  "  +
                "   <tr>  "  +
                "   <td align='left' bgcolor='#cccccc' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#cccccc'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' class='es-infoblock' style='padding:0;Margin:0;line-height:120%;font-size:0;color:#CCCCCC'>  "  +
                "   <table cellpadding='0' cellspacing='0' class='es-table-not-adapt es-social' role='presentation' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;padding-right:10px'><img title='Facebook' src='https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/facebook-circle-colored.png' alt='Facebookb' width='32' style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic'></td>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;padding-right:10px'><img title='Twitter' src='https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/twitter-circle-colored.png' alt='Twitter' width='32' style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic'></td>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;padding-right:10px'><img title='Instagram' src='https://rywkbv.stripocdn.email/content/assets/img/social-icons/circle-colored/instagram-circle-colored.png' alt='Instagram' width='32' style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic'></td>  "  +

                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table>  "  +
                "   <table cellpadding='0' cellspacing='0' class='es-content' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%'>  "  +
                "   <tr>  "  +
                "   <td align='center' style='padding:0;Margin:0'>  "  +
                "   <table bgcolor='#ffffff' class='es-content-body' align='center' cellpadding='0' cellspacing='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px'>  "  +
                "   <tr>  "  +
                "   <td align='left' bgcolor='#cccccc' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;background-color:#cccccc'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' style='padding:0;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial,helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'>Av. del Libertador 5420, CABA, Argentina<br><br></p></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   <tr>  "  +
                "   <td align='left' bgcolor='#999999' style='padding:20px;Margin:0;background-color:#999999'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>  "  +
                "   <table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>  "  +
                "   <tr>  "  +
                "   <td align='center' class='es-infoblock' style='padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial,helvetica, sans-serif;line-height:14px;color:#ffffff;font-size:12px'>No longer want to receive these emails?&nbsp;<a href='' target='_blank' style='-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#ffffff;font-size:12px'>Unsubscribe</a>.</p></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table></td>  "  +
                "   </tr>  "  +
                "   </table>  "  +
                "   </div>  "  +
                "   </body>  "  +
                "  </html>  " ;
    }
}
